package com.example.minichat

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.MessagesAdapter
import com.example.minichat.database.AppDatabase
import com.example.minichat.databinding.ActivityChatBinding
import com.example.minichat.entities.ChatEntity
import com.example.minichat.entities.MensajeEntity
import com.example.minichat.entities.UsuarioPerfil
import com.example.minichat.services.ChatService
import com.example.minichat.services.ObserversWebsocketImpl

class ChatActivity : AppCompatActivity() {
  private lateinit var binding: ActivityChatBinding
  private val db = AppDatabase.getDatabase(this)
  private val manager = LinearLayoutManager(this)
  private var chat: ChatEntity? = null
  private var usuario: UsuarioPerfil? = null
  private var isBound = false
  private var chatService: ChatService? = null
  private var loginUsuarioData = db.loginUsuarioDao().getLoginUsuario()

  private val observerWebsocket = object : ObserversWebsocketImpl() {

    override fun observeNewMessage() {
      runOnUiThread {
        initRecyclerView()
      }
    }

    override fun observeNewReaction() {
      runOnUiThread {
        initRecyclerView()
      }
    }

    override fun observeJoinedRoom() {
      runOnUiThread {
        initRecyclerView()
      }
    }
  }

  private val connection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      val binder = service as ChatService.ChatServiceBinder
      chatService = binder.getService()
      binder.setObserversWebsocket(observerWebsocket)
      isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      isBound = false
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityChatBinding.inflate(layoutInflater)
    setContentView(binding.root)
    binding.recyclerChat.layoutManager = manager

    try {
      val intent = intent
      if (intent != null) {
        val chatId = intent.getLongExtra("chatId", 0L)
        val usuarioId = intent.getLongExtra("usuarioId", 0L)

        chat = db.chatDao().getById(chatId)
        usuario = db.usuarioDao().getUsuarioById(usuarioId)

        binding.textViewChatName.text = usuario?.perfil?.nombre ?: "Chat"
      }
    } catch (e: Exception) {
      e.printStackTrace()
    }

    this.initRecyclerView()

    Intent(this, ChatService::class.java).also { intent ->
      bindService(intent, connection, BIND_AUTO_CREATE)
    }

    binding.buttonSendMessage.setOnClickListener {
      sendMessage()
    }

    val loginUsuarioData = db.loginUsuarioDao().getLoginUsuario()
    val dispositivoVinculado = db.dispositivoVinculadoDao()
      .getDispositivoVinculado(loginUsuarioData?.usuarioPerfil?.usuario?.id!!)

    /*val toolbar : Toolbar = findViewById(R.id.toolbarChat)
    setSupportActionBar(toolbar)
    getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
    getSupportActionBar()?.setDisplayShowHomeEnabled(true)*/
  }

  override fun onDestroy() {
    super.onDestroy()
    if (isBound) {
      unbindService(connection)
      isBound = false
    }
  }

  private fun sendMessage() {
    val mensaje = binding.inputMessage.text.toString()
    if (mensaje.isNotEmpty()) {
      chatService?.sendMessage(
        MensajeEntity(
          mensaje = mensaje,
          idChat = chat?.id,
          idUsuario = loginUsuarioData?.usuarioPerfil?.usuario?.id!!,
        )
      )
      binding.inputMessage.text?.clear()
    }
  }


  fun initRecyclerView() {
    if (chat?.id != null) {
      val mensajes = db.mensajeDao().getMensajesChat(chat?.id!!)
      val loginUsuarioEntity = db.loginUsuarioDao().getLoginUsuario()

      binding.recyclerChat.adapter = MessagesAdapter(mensajes, loginUsuarioEntity)

      // Scroll to the last message
      binding.recyclerChat.scrollToPosition(mensajes.size - 1)
    }
  }

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.menu, menu)
    return super.onCreateOptionsMenu(menu)
  }

  override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item.itemId) {
      R.id.itemProfile -> Profile()
      R.id.itemSetting -> Setting()
      R.id.itemCreateGroup -> CreateGroup()
      R.id.itemBlockedContact -> BlockedContact()
      android.R.id.home -> finish()
    }
    return super.onOptionsItemSelected(item)
  }

  fun Profile() {
    Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, ProfileActivity::class.java)
    startActivity(intent)
  }

  fun Setting() {
    Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, SettingActivity::class.java)
    startActivity(intent)
  }

  fun CreateGroup() {
    Toast.makeText(this, "Crear Grupo", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, CreateGroupActivity::class.java)
    startActivity(intent)
  }

  fun BlockedContact() {
    Toast.makeText(this, "Contactos Bloqueados", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, BlockedContactsActivity::class.java)
    startActivity(intent)
  }
}