package com.example.minichat.datasource

import android.content.Context
import android.util.Log
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod

class RestDataSourceChat {
	companion object {
		fun chats(context: Context, idUsuario: Long, useToken: String, callback: (String) -> Unit) {
			try {
				RequestInstance(
					RequestMethod.GET,
					"/chat/usuario/$idUsuario"
				)
					.addAuthToken(useToken)
					.addAcceptHeaderJson()
					.executeAsync(context) { response ->
						if (response != null) {
							try {
								callback(response.body?.string().toString())
							} catch (e: Exception) {
								Log.e("RestDataSourceChat", e.toString())
							}
						}
					}
			} catch (e: Exception) {
				callback("")
			}
		}

		fun mensajesChats(
			context: Context,
			idChat: Long?,
			useToken: String,
			callback: (String) -> Unit
		) {
			RequestInstance(
				RequestMethod.GET,
				"/chat/$idChat/mensajes"
			)
				.addAuthToken(useToken)
				.addAcceptHeaderJson()
				.executeAsync(context) { response ->

					val responseBody = response?.body?.string().toString()

					if (response != null && responseBody.isNotEmpty()) {
						try {
							callback(responseBody)
						} catch (e: Exception) {
							Log.e("RestDataSourceChat", e.toString())
						}
					}
				}
		}
	}
}