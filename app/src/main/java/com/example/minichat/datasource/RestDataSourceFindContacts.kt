package com.example.minichat.datasource

import android.content.Context
import com.example.minichat.dataclasses.ContactFind
import com.example.minichat.dataclasses.Perfil
import com.example.minichat.http.RequestInstance
import com.example.minichat.http.RequestMethod
import org.json.JSONArray

class RestDataSourceFindContacts {
  companion object {
    fun findContacts(
      context: Context,
      idUsuario: Long?,
      token: String?,
      callback: (List<ContactFind>) -> Unit
    ) {
      RequestInstance(
        RequestMethod.GET,
        "/contacts/find-contacts/${idUsuario}"
      )
        .addAcceptHeaderJson()
        .addAuthToken(token ?: "")
        .executeAsync(
          context
        ) { response ->
          try {
            val contacts: ArrayList<ContactFind> = arrayListOf()
            val responseBody = response?.body?.string().toString()
            responseBody.let {

              val jsonArrayObject = JSONArray(responseBody)

              for (i in 0 until jsonArrayObject.length()) {
                // Contact
                val jsonObject = jsonArrayObject.getJSONObject(i)
                val id = jsonObject.getLong("id")
                val nombre = jsonObject.getString("nombre")
                val createdAt = jsonObject.getString("createdAt")
                val updatedAt = jsonObject.getString("updatedAt")
                val contactoAceptado = jsonObject.getBoolean("contacto_aceptado")
                // Perfil
                val perfil = jsonObject.getJSONObject("perfil")
                val nombrePerfil = perfil.getString("nombre")
                val fotoPerfil = perfil.getString("foto")

                val contact = ContactFind(
                  id = id,
                  nombre = nombre,
                  createdAt = createdAt,
                  updatedAt = updatedAt,
                  contacto_aceptado = contactoAceptado,
                  perfil = Perfil(nombrePerfil, fotoPerfil)
                )

                contacts.add(contact)
              }
            }
            callback(contacts)
          } catch (e: Exception) {
            e.printStackTrace()
            callback(emptyList())
          }
        }
    }
  }
}