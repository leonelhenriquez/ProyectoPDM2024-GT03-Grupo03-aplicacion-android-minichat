package com.example.minichat.datasource

import android.content.Context
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod

class RestDataSourceChat {
	companion object {
		fun chats(context: Context, idUsuario: Long, useToken: String, callback: (String) -> Unit) {
			RequestInstance(
				RequestMethod.GET,
				"/chat/usuario/$idUsuario"
			)
				.addAuthToken(useToken)
				.addAcceptHeaderJson()
				.executeAsync(context) { response ->
					if (response != null) {
						callback(response.body?.string().toString())
					}
				}
		}
	}
}