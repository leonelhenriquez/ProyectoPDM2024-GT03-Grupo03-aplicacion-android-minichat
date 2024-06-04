package com.example.minichat.datasource

import android.content.Context
import android.util.Log
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod

class RestDataSourcePerfil {
  companion object {
    fun getPerfilUsuario(
      context: Context,
      idUsuario: Long?,
      token: String?,
      callback: (String) -> Unit
    ) {
      RequestInstance(
        RequestMethod.GET,
        "/usuario/${idUsuario}/perfil"
      )
        .addAcceptHeaderJson()
        .addAuthToken(token ?: "")
        .executeAsync(
          context
        ) { response ->
          try {
            val responseBody = response?.body?.string()
            responseBody?.let {
              callback(responseBody.toString())
            }
          } catch (e: Exception) {
            e.printStackTrace()
            Log.e("RestDataSourcePerfil", e.toString())
          }
        }
    }
  }
}