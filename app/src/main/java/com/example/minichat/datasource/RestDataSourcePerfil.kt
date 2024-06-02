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
					val responseBody = response?.body?.string().toString()
					if (responseBody.isNotEmpty()) {
						try {
							callback(responseBody)
						} catch (e: Exception) {
							Log.e("RestDataSourcePerfil", e.toString())
						}

					}
				}
		}
	}
}