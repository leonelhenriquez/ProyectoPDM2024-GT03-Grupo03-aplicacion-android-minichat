package com.example.minichat.datasource

import android.content.Context
import android.util.Log
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod
import com.google.gson.JsonObject
import okhttp3.Response

class RestDataSourceAuth {

	companion object {
		fun auth(
			username: String,
			password: String,
			deviceName: String,
			context: Context,
			callback: (Response?) -> Unit
		) {

			try {
				val jsonObject = JsonObject()
				jsonObject.addProperty("username", username)
				jsonObject.addProperty("password", password)
				jsonObject.addProperty("nombreDispositivo", deviceName)

				RequestInstance(
					RequestMethod.POST,
					"/auth"
				)
					.addJsonBody(jsonObject.toString())
					.addAcceptHeaderJson()
					.executeAsync(
						context
					) { responseBody ->
						try {
							callback(responseBody)
						} catch (e: Exception) {
							Log.e("RestDataSourceAuth", e.toString())
						}
					}
			} catch (e: Exception) {
				callback(null)
			}
		}
	}
}