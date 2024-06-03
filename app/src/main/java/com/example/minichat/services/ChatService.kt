package com.example.minichat.services

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import com.example.minichat.database.AppDatabase
import com.example.minichat.datasource.RestDataSourceChat
import com.example.minichat.datasource.RestDataSourcePerfil
import com.example.minichat.entities.ChatEntity
import com.example.minichat.entities.MensajeEntity
import com.example.minichat.entities.PerfilEntity
import com.example.minichat.entities.PreferenciaChatEntity
import com.example.minichat.entities.TipoChatEntity
import com.example.minichat.entities.UsuarioChatEntity
import com.example.minichat.entities.UsuarioEntity
import io.socket.client.IO
import io.socket.client.Socket
import okhttp3.OkHttpClient
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat

class ChatService : Service(), ChatServiceInterface {

  private var chatServiceSocketIo: Socket? = null

  private var db: AppDatabase? = null

  private val handler = Handler(Looper.getMainLooper())

  private val binder = ChatServiceBinder()

  private val clientObserversWebsocket: ArrayList<ObserversWebsocket> = arrayListOf()

  inner class ChatServiceBinder : Binder() {
    fun getService(): ChatService = this@ChatService

    fun setObserversWebsocket(observersWebsocket: ObserversWebsocket) {
      clientObserversWebsocket.add(observersWebsocket)
    }

    fun removeObserversWebsocket(observersWebsocket: ObserversWebsocket) {
      clientObserversWebsocket.remove(observersWebsocket)
    }
  }

  override fun onBind(intent: Intent): IBinder {
    Log.v("ChatService", "Service binded")
    return binder
  }

  override fun onCreate() {
    Log.v("ChatService", "Service started")
    super.onCreate()
    this.dbInit()
    this.getChats()
    this.getPerfiles()
    this.getMessagesChats()
    this.wsIniSocketIo()
    handler.post(loadDataRunnable)

    try {
      for (observer in clientObserversWebsocket) {
        callBackgroundService {
          observer.observeNewMessage()
        }
      }
    } catch (e: Exception) {
      Log.e("ChatService[onCreate]", e.toString())
    }
  }

  override fun onDestroy() {
    Log.v("ChatService", "Service destroyed")
    super.onDestroy()
    this.chatServiceSocketIo?.disconnect()
    this.handler.removeCallbacks(loadDataRunnable)
  }

  private fun dbInit() {
    this.db = AppDatabase.getDatabase(this)
  }

  private fun wsIniSocketIo() {
    val loginUsuario = this.db?.loginUsuarioDao()?.getLoginUsuario()

    val okHttpClient = OkHttpClient
      .Builder()
      .addInterceptor { chain ->
        val originalRequest = chain.request()
        val requestWithHeaders = originalRequest.newBuilder()
          .addHeader("Authorization", loginUsuario?.loginUsuarioEntity?.token ?: "")
          .addHeader("Content-Type", "application/json")
          .addHeader("Accept", "application/json")
          .build()
        chain.proceed(requestWithHeaders)
      }
      .build()

    IO.setDefaultOkHttpWebSocketFactory(okHttpClient)
    IO.setDefaultOkHttpCallFactory(okHttpClient)
    this.chatServiceSocketIo = IO.socket("https://pdm.h130.dev")
    this.chatServiceSocketIo?.connect()

    this.chatServiceSocketIo?.on(Socket.EVENT_CONNECT) {
      Log.v("ChatService[wsIniSocketIo]", "Conexión abierta")
    }

    this.observeNewMessage()
    this.observeNewReaction()
    this.observeJoinedRoom()
  }

  override fun sendMessage(message: MensajeEntity) {

    val messageJsonObject = JSONObject()
    messageJsonObject.put("idChat", message.idChat)
    messageJsonObject.put("idUsuario", message.idUsuario)
    messageJsonObject.put("mensaje", message.mensaje)

    val messageJson = messageJsonObject.toString()
    Log.v("ChatService[sendMessage]", messageJson)

    this.chatServiceSocketIo?.emit("send-message", messageJson)

    this.loadData()

  }

  override fun observeNewMessage() {
    this.chatServiceSocketIo?.on("new-message") { args ->

      loadData()

      try {
        Log.v("ChatService[observeNewMessage]", args[0].toString())

        val message = args[0].toString()

        Log.v("ChatService[observeNewMessage]", message)

        val jsonObject = JSONObject(message)
        val messageJsonObject = jsonObject.getJSONObject("message")
        val messageEntity = MensajeEntity(
          id = messageJsonObject.getLong("id"),
          mensaje = messageJsonObject.getString("mensaje"),
          fechaHora = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
            messageJsonObject.getString(
              "fechaHora"
            )
          ),
          idUsuario = messageJsonObject.getLong("idUsuario"),
          idChat = messageJsonObject.getLong("idChat"),
          updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
            messageJsonObject.getString(
              "updatedAt"
            )
          )
        )

        val usuarioEntity = UsuarioEntity(
          id = messageJsonObject.getLong("usuario.id"),
          nombre = messageJsonObject.getString("usuario.nombre"),
        )

        db?.usuarioDao()?.upsert(usuarioEntity)
        db?.mensajeDao()?.upsert(messageEntity)
      } catch (e: Exception) {
        e.printStackTrace()
        Log.e("ChatService[observeNewMessage]", e.toString())
      }

      try {
        for (observer in clientObserversWebsocket) {
          callBackgroundService {
            observer.observeNewMessage()
          }
        }
      } catch (e: Exception) {
        e.printStackTrace()
        Log.e("ChatService[observeNewMessage]", e.toString())
      }
    }

  }

  override fun observeNewReaction() {
    this.chatServiceSocketIo?.on("new-reaction") { args ->
      try {
        Log.v("ChatService[observeNewReaction]", args[0].toString())

        for (observer in clientObserversWebsocket) {
          callBackgroundService {
            observer.observeNewReaction()
          }
        }
      } catch (e: Exception) {
        Log.e("ChatService[observeNewReaction]", e.toString())
      }
    }
  }

  override fun observeJoinedRoom() {
    this.chatServiceSocketIo?.on("joined-room") { args ->
      try {
        Log.v("ChatService[observeJoinedRoom]", args[0].toString())

        for (observer in clientObserversWebsocket) {
          callBackgroundService {
            observer.observeJoinedRoom()
          }
        }
      } catch (e: Exception) {
        Log.e("ChatService[observeJoinedRoom]", e.toString())
      }
    }
  }

  private fun getChats() {
    val loginUsuario = this.db?.loginUsuarioDao()?.getLoginUsuario()
    RestDataSourceChat.chats(
      this,
      loginUsuario?.loginUsuarioEntity?.idUsuario ?: 0,
      loginUsuario?.loginUsuarioEntity?.token ?: ""
    ) { response ->
      val jsonArrayJSONObject = JSONArray(response)
      for (i in 0 until jsonArrayJSONObject.length()) {
        val jsonObject = jsonArrayJSONObject.getJSONObject(i)

        Log.v("ChatService[getChats]", jsonObject.toString())


        val tipoChatJSONObject = jsonObject.getJSONObject("tipoChat")
        var tipoChatEntity = TipoChatEntity()
        try {
          tipoChatEntity = TipoChatEntity(
            id = tipoChatJSONObject.getLong("id"),
            nombre = tipoChatJSONObject.getString("nombre")
          )
          db?.tipoChatDao()?.upsert(tipoChatEntity)
        } catch (e: Exception) {
        }

        val chatEntity = ChatEntity(
          id = jsonObject.getLong("id"),
          uriFoto = null,
          idTipoChat = tipoChatEntity.id,
          fechaCreacion = null,
          updatedAt = null
        )
        db?.chatDao()?.upsert(chatEntity)

        val miembrosJsonArrayObject = jsonObject.getJSONArray("miembros")
        for (j in 0 until miembrosJsonArrayObject.length()) {
          val miembroJsonObject = miembrosJsonArrayObject.getJSONObject(j)

          val usuarioEntity = UsuarioEntity(
            id = miembroJsonObject.getLong("id_usuario"),
            nombre = miembroJsonObject.getJSONObject("usuario").getString("nombre"),
            createdAt = null,
            updatedAt = null
          )

          val userChat =
            db?.usuarioChatDao()?.getUsuarioChat(usuarioEntity.id, jsonObject.getLong("id"))

          var usuarioChatEntity: UsuarioChatEntity? = null
          if (userChat?.id != null) {
            usuarioChatEntity = UsuarioChatEntity(
              id = userChat.id,
              idUsuario = usuarioEntity.id,
              idChat = jsonObject.getLong("id"),
              idRol = null,
              createdAt = null,
              updatedAt = null
            )
          } else {
            usuarioChatEntity = UsuarioChatEntity(
              idUsuario = usuarioEntity.id,
              idChat = jsonObject.getLong("id"),
              idRol = null,
              createdAt = null,
              updatedAt = null
            )
          }

          db?.usuarioDao()?.upsert(usuarioEntity)
          if (usuarioChatEntity.id != null) {
            db?.usuarioChatDao()?.upsert(usuarioChatEntity)
          } else {
            db?.usuarioChatDao()?.insert(usuarioChatEntity)
          }
        }

        val preferenciasChatsJsonArrayObject = jsonObject.getJSONArray("preferencias")
        for (j in 0 until preferenciasChatsJsonArrayObject.length()) {

          try {
            val preferenciaChatJsonObject = preferenciasChatsJsonArrayObject.getJSONObject(j)

            val preferenciaChatEntity = PreferenciaChatEntity(
              id = preferenciaChatJsonObject.getLong("id"),
              nombre = preferenciaChatJsonObject.getString("nombre"),
              fondoColor = preferenciaChatJsonObject.getString("fondo_color"),
              idUsuario = preferenciaChatJsonObject.getLong("id_usuario"),
              idChat = chatEntity.id,
              createdAt = null,
              updatedAt = null
            )

            db?.preferenciaChatDao()?.upsert(preferenciaChatEntity)
          } catch (e: Exception) {
            Log.e("ChatService[getChats][preferenciaChatEntity]", e.toString())
          }
        }

        val mensajesJsonArrayObject = jsonObject.getJSONArray("mensajes")
        for (k in 0 until mensajesJsonArrayObject.length()) {
          val mensajeJsonObject = mensajesJsonArrayObject.getJSONObject(k)

          val mensajeEntity = MensajeEntity(
            id = mensajeJsonObject.getLong("id"),
            mensaje = mensajeJsonObject.getString("mensaje"),
            fechaHora = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
              mensajeJsonObject.getString("fechaHora")
            ),
            idUsuario = mensajeJsonObject.getLong("idUsuario"),
            idChat = mensajeJsonObject.getLong("idChat"),
            updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
              mensajeJsonObject.getString("updatedAt")
            )
          )
          db?.mensajeDao()?.upsert(mensajeEntity)
        }
      }
    }
  }

  fun getMessagesChats() {
    try {

      val chats = db?.chatDao()?.getChats()
      val loginUsuario = db?.loginUsuarioDao()?.getLoginUsuario()

      if (chats != null) {
        for (chat in chats) {
          RestDataSourceChat.mensajesChats(
            this,
            chat.id,
            loginUsuario?.loginUsuarioEntity?.token ?: ""
          ) { response ->
            val jsonArrayJSONObject = JSONArray(response)
            for (i in 0 until jsonArrayJSONObject.length()) {
              val jsonObject = jsonArrayJSONObject.getJSONObject(i)

              val mensajeEntity = MensajeEntity(
                id = jsonObject.getLong("id"),
                mensaje = jsonObject.getString("mensaje"),
                fechaHora = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
                  jsonObject.getString("fechaHora")
                ),
                idUsuario = jsonObject.getLong("idUsuario"),
                idChat = jsonObject.getLong("idChat"),
                updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
                  jsonObject.getString("updatedAt")
                )
              )
              db?.mensajeDao()?.upsert(mensajeEntity)

              for (observer in clientObserversWebsocket) {
                callBackgroundService {
                  observer.observeNewMessage()
                }
              }
            }
          }
        }
      }
    } catch (e: Exception) {
    }
  }

  fun getPerfiles() {
    try {
      val usuarios = db?.usuarioDao()?.getUsuarios()
      val loginUsuario = db?.loginUsuarioDao()?.getLoginUsuario()

      if (usuarios != null) {
        for (usuario in usuarios) {
          if (usuario.id != null) {
            RestDataSourcePerfil.getPerfilUsuario(
              this,
              usuario.id,
              loginUsuario?.loginUsuarioEntity?.token
            ) { response ->

              if (!response.isEmpty()) {

                val perfilJSONObject = JSONObject(response)

                val perfilEntity = PerfilEntity(
                  id = perfilJSONObject.getLong("id"),
                  nombre = perfilJSONObject.getString("nombre"),
                  biografia = perfilJSONObject.getString("biografia"),
                  foto = perfilJSONObject.getString("foto"),
                  correo = perfilJSONObject.getString("correo"),
                  idUsuario = perfilJSONObject.getLong("idUsuario"),
                  createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
                    perfilJSONObject.getString("createdAt")
                  ),
                  updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
                    perfilJSONObject.getString("updatedAt")
                  )
                )

                db?.perfilDao()?.upsert(perfilEntity)
              }
            }
          }
        }
      }
    } catch (e: Exception) {
    }
  }

  private fun loadData() {
    getChats()
    getPerfiles()
    getMessagesChats()
  }

  private val loadDataRunnable = object : Runnable {
    override fun run() {
      loadData()
      for (observer in clientObserversWebsocket) {
        callBackgroundService {
          observer.observeNewMessage()
        }
      }
      handler.postDelayed(this, 1000 * 60) // Vuelve a ejecutar después de N segundos
    }
  }

  private fun callBackgroundService(callback: () -> Unit) {
    (object : Thread() {
      override fun run() {
        callback()
      }
    }).start()
  }

}