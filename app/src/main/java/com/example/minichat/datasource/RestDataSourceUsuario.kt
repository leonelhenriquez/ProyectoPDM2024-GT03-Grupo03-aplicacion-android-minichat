package com.example.minichat.datasource

import android.content.Context
import android.util.Log
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod
import com.google.gson.JsonObject
import okhttp3.Response

class RestDataSourceUsuario {
	companion object {
		fun register(
			username: String,
			password: String,
			nombre: String,
			email: String,
			deviceName: String,
			context: Context,
			callback: (Response?) -> Unit
		) {
			val jsonObject = JsonObject()
			jsonObject.addProperty("nombre", nombre)
			jsonObject.addProperty("contra", password)
			jsonObject.addProperty("correo", email)
			jsonObject.addProperty("nombre_usuario", username)
			jsonObject.addProperty("nombreDispositivo", deviceName)

			RequestInstance(
				RequestMethod.POST,
				"/usuario"
			)
				.addJsonBody(jsonObject.toString())
				.addAcceptHeaderJson()
				.executeAsync(
					context
				) { response ->
					try {
						callback(response)
					} catch (e: Exception) {
						Log.e("RestDataSourceUsuario", e.toString())
					}
				}
		}
	}
}