package com.example.minichat

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.minichat.database.AppDatabase
import com.example.minichat.datasource.RestDataSourceAuth
import com.example.minichat.datasource.RestDataSourceUsuario
import com.example.minichat.entities.DispositivoVinculadoEntity
import com.example.minichat.entities.LoginUsuarioEntity
import com.example.minichat.entities.PerfilEntity
import com.example.minichat.entities.UsuarioEntity
import es.dmoral.toasty.Toasty
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat

class RegisterActivity : AppCompatActivity() {

  private var db: AppDatabase? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_register)

    db = AppDatabase.getDatabase(this)

    val btnSingUp: Button = findViewById(R.id.btnSignUpReg)
    val btnBack: Button = findViewById(R.id.btnBackReg)

    val inputNombre: EditText = findViewById(R.id.inputNameReg)
    val inputEmail: EditText = findViewById(R.id.inputEmailReg)
    val inputUsername: EditText = findViewById(R.id.inputUserReg)
    val inputPassword: EditText = findViewById(R.id.inputPasswordReg)

    btnSingUp.setOnClickListener {
      val nombre = inputNombre.text.toString()
      val email = inputEmail.text.toString()
      val username = inputUsername.text.toString()
      val password = inputPassword.text.toString()
      val deviceName = android.os.Build.MODEL

      if (
        nombre.isEmpty() ||
        email.isEmpty() ||
        username.isEmpty() ||
        password.isEmpty()
      ) {
        Toasty.error(
          this,
          "Por favor, rellene todos los campos",
          Toasty.LENGTH_SHORT,
          true
        ).show()
        return@setOnClickListener
      }


      RestDataSourceUsuario.register(
        username,
        password,
        nombre,
        email,
        deviceName,
        context = this
      ) { response ->
        try {
          val responseBody = response?.body?.string().toString()
          Log.d("RESPONSE", responseBody)
          if (response == null) {
            Toasty.error(
              this,
              "Error al registrar el usuario",
              Toasty.LENGTH_SHORT,
              true
            ).show()
            return@register
          }

          if (response.code != 200 && response.code != 201) {
            var message = ""
            try {
              val messages: JSONArray = JSONObject(responseBody).getJSONArray("message")
              for (i in 0 until messages.length()) {
                message += messages.getString(i) + "\n"
              }
            } catch (e: Exception) {
              message = ""
            }

            try {
              if (message.isEmpty()) {
                message = JSONObject(responseBody).getString("message")
              }
            } catch (e: Exception) {
              message = ""
            }

            if (message.isEmpty()) {
              message = "Error al registrar el usuario"
            }

            Log.v("RegisterActivity", message)

            Toasty.error(
              this,
              message.trim(),
              Toasty.LENGTH_SHORT,
              true
            ).show()
            return@register
          }

          this.saveAccount(responseBody)


          Toasty.success(
            this,
            "Error al registrar el usuario",
            Toasty.LENGTH_SHORT,
            true
          ).show()


          RestDataSourceAuth.auth(
            username,
            password,
            android.os.Build.MODEL,
            context = this
          ) { responseLogin ->
            val loginUsuarioEntity = this.saveAccountLogin(responseLogin?.body?.string().toString())
            if (loginUsuarioEntity.tfaPasado == true) {
              val intent = Intent(this, ChatsActivity::class.java)
              startActivity(intent)
            } else {
              val intent = Intent(this, A2fActivity::class.java)
              startActivity(intent)
            }
          }

          return@register
        } catch (e: Exception) {
          Log.e("ERROR", e.toString())
          Toasty.error(
            this,
            "Usuario registrado correctamente",
            Toasty.LENGTH_SHORT,
            true
          ).show()
        }
      }

      /*
      val intent = Intent(this, ChatsActivity::class.java)
      startActivity(intent)
      */
    }

    btnBack.setOnClickListener {
      val intent = Intent(this, LoginActivity::class.java)
      startActivity(intent)
    }
  }

  @SuppressLint("SimpleDateFormat")
  fun saveAccount(jsonString: String) {
    val jsonObject = JSONObject(jsonString)
    val perfilJsonObject = jsonObject.getJSONObject("perfil")

    val usuarioEntity = UsuarioEntity(
      id = jsonObject.getLong("id"),
      nombre = jsonObject.getString("nombre"),
      createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        jsonObject.getString(
          "createdAt"
        )
      ),
      updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        jsonObject.getString(
          "updatedAt"
        )
      )
    )

    val perfilEntity = PerfilEntity(
      id = perfilJsonObject.getLong("id"),
      nombre = perfilJsonObject.getString("nombre"),
      correo = perfilJsonObject.getString("correo"),
      idUsuario = perfilJsonObject.getLong("idUsuario"),
      updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        perfilJsonObject.getString(
          "updatedAt"
        )
      ),
      createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        perfilJsonObject.getString(
          "createdAt"
        )
      ),
      biografia = perfilJsonObject.getString("biografia"),
      foto = perfilJsonObject.getString("foto")
    )

    db?.usuarioDao()?.upsert(usuarioEntity)
    db?.perfilDao()?.upsert(perfilEntity)
  }

  @SuppressLint("SimpleDateFormat")
  fun saveAccountLogin(jsonString: String): LoginUsuarioEntity {
    val jsonObject = JSONObject(jsonString)
    val usuario = jsonObject.getJSONObject("usuario")

    val usuarioEntity = UsuarioEntity(
      id = usuario.getLong("id"),
      nombre = usuario.getString("nombre"),
      createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(usuario.getString("createdAt")),
      updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(usuario.getString("updatedAt"))
    )

    val dispositivoVinculado = jsonObject.getJSONObject("dispositivo")

    val dispositivoVinculadoEntity = DispositivoVinculadoEntity(
      id = dispositivoVinculado.getLong("id"),
      nombreDispositivo = dispositivoVinculado.getString("nombreDispositivo"),
      token = dispositivoVinculado.getString("token"),
      idUsuario = dispositivoVinculado.getLong("idUsuario"),
      fecha = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        dispositivoVinculado.getString(
          "fecha"
        )
      ),
      updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(
        dispositivoVinculado.getString(
          "updatedAt"
        )
      )
    )

    val loginUsuarioEntity = LoginUsuarioEntity(
      idUsuario = usuarioEntity.id,
      idDispositivo = dispositivoVinculadoEntity.id,
      token = dispositivoVinculadoEntity.token,
      tfaRequerido = usuario.getBoolean("tfaRequerido"),
      tfaPasado = usuario.getBoolean("tfaPasado")
    )

    db?.usuarioDao()?.upsert(usuarioEntity)
    db?.dispositivoVinculadoDao()?.upsert(dispositivoVinculadoEntity)
    db?.loginUsuarioDao()?.nukeTable()
    db?.loginUsuarioDao()?.upsert(loginUsuarioEntity)

    return loginUsuarioEntity
  }
}