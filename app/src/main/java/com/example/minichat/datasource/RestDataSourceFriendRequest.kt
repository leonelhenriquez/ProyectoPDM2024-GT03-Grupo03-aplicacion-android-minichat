package com.example.minichat.datasource

import android.content.Context
import android.util.Log
import com.example.minichat.dataclasses.FriendRequest
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod
import org.json.JSONArray
import org.json.JSONObject

class RestDataSourceFriendRequest {
  companion object {
    fun countFriendRequests(
      idUsuario: Long?,
      token: String?,
      context: Context,
      callback: (Int?) -> Unit
    ) {
      try {
        RequestInstance(
          RequestMethod.GET,
          "/contacts/list-friend-requests/${idUsuario}"
        )
          .addAuthToken(token ?: "")
          .addAcceptHeaderJson()
          .executeAsync(
            context
          ) { response ->
            try {

              val responseBody = response?.body?.string()
              responseBody?.let {

                Log.d("RestDataSourceFriendRequest", responseBody)
                val countRequests = JSONArray(responseBody).length()
                callback(countRequests)
              }
            } catch (e: Exception) {
              e.printStackTrace()
              Log.e("RestDataSourceFriendRequest", e.toString())
            }
          }
      } catch (e: Exception) {
        callback(null)
      }
    }

    fun friendRequests(
      idUsuario: Long?,
      token: String?,
      context: Context,
      callback: (List<FriendRequest>) -> Unit
    ) {
      try {
        RequestInstance(
          RequestMethod.GET,
          "/contacts/list-friend-requests/${idUsuario}"
        )
          .addAuthToken(token ?: "")
          .addAcceptHeaderJson()
          .executeAsync(
            context
          ) { response ->
            try {
              val friendRequestList: ArrayList<FriendRequest> = arrayListOf()

              val responseBody = response?.body?.string().toString()
              responseBody.let {
                val jsonArrayObject = JSONArray(responseBody)
                for (i in 0 until jsonArrayObject.length()) {
                  val jsonObject = jsonArrayObject.getJSONObject(i)

                  val id = jsonObject.getLong("id")
                  val aceptado = jsonObject.getBoolean("aceptado")

                  val usuario = jsonObject.getJSONObject("usuario")
                  val idUsuarioJson = usuario.getLong("id")
                  val nombreUsuario = usuario.getString("nombre")

                  val perfil = usuario.getJSONObject("perfil")
                  val nombrePerfil = perfil.getString("nombre")
                  val fotoPerfil = perfil.getString("foto")

                  val friendRequest = FriendRequest(
                    id = id,
                    aceptado = aceptado,
                    usuario = com.example.minichat.dataclasses.UsuarioFriendRequest(
                      id = idUsuarioJson,
                      nombre = nombreUsuario,
                      perfil = com.example.minichat.dataclasses.PerfilFriendRequest(
                        nombre = nombrePerfil,
                        foto = fotoPerfil
                      )
                    )
                  )

                  friendRequestList.add(friendRequest)
                }
              }

              callback(friendRequestList)
            } catch (e: Exception) {
              e.printStackTrace()
              Log.e("RestDataSourceFriendRequest", e.toString())
            }
          }
      } catch (e: Exception) {
        e.printStackTrace()
        callback(emptyList())
      }
    }

    fun acceptFriendRequest(
      idUsuario: Long?,
      idContacto: Long?,
      token: String?,
      context: Context,
      callback: () -> Unit
    ) {
      try {

        val friendRequestObject = JSONObject()
        friendRequestObject.put("idUsuario", idUsuario)
        friendRequestObject.put("idContacto", idContacto)

        RequestInstance(
          RequestMethod.POST,
          "/contacts/accept-friend-request"
        )
          .addAuthToken(token ?: "")
          .addJsonBody(friendRequestObject.toString())
          .addAcceptHeaderJson()
          .executeAsync(
            context
          ) { response ->
            try {
              val responseBody = response?.body?.string().toString()
              responseBody.let {
                callback()
              }
            } catch (e: Exception) {
              e.printStackTrace()
              Log.e("RestDataSourceFriendRequest", e.toString())
            }
          }
      } catch (e: Exception) {
        e.printStackTrace()
        callback()
      }
    }
  }
}