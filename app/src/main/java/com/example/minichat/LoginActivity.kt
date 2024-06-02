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
import com.example.minichat.entities.DispositivoVinculadoEntity
import com.example.minichat.entities.LoginUsuarioEntity
import com.example.minichat.entities.UsuarioEntity
import es.dmoral.toasty.Toasty
import org.json.JSONObject
import java.text.SimpleDateFormat

class LoginActivity : AppCompatActivity() {

	private var db: AppDatabase? = null

	@SuppressLint("SimpleDateFormat")
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_login)

		db = AppDatabase.getDatabase(this)

		this.checkAccount()

		val btnSingIn: Button = findViewById(R.id.btnSignin)
		val btnSingUp: Button = findViewById(R.id.btnSignup)

		val textFieldUsername: EditText = findViewById(R.id.inputUsername)
		val textFieldPassword: EditText = findViewById(R.id.inputPassword)


		btnSingIn.setOnClickListener {

			if (textFieldUsername.text.toString().isEmpty() || textFieldPassword.text.toString()
					.isEmpty()
			) {
				Toasty.error(
					this,
					"Por favor, rellene todos los campos",
					Toasty.LENGTH_SHORT,
					true
				).show()
				return@setOnClickListener
			}

			try {
				RestDataSourceAuth.auth(
					textFieldUsername.text.toString(),
					textFieldPassword.text.toString(),
					android.os.Build.MODEL,
					context = this
				) { response ->
					val responseBody = response?.body?.string().toString()


					if (response?.code == 401) {
						val jsonObject = JSONObject(responseBody)
						val message = jsonObject.getString("message")
						Toasty.error(
							this,
							message,
							Toasty.LENGTH_SHORT,
							true
						).show()
						return@auth
					}


					if (response == null || response.code != 201) {
						Toasty.error(
							this,
							"Error al iniciar sesión",
							Toasty.LENGTH_SHORT,
							true
						).show()
						return@auth
					}
					//Log.v("LoginActivity",responseBody)

					val loginUsuarioEntity = this.saveAccount(responseBody)

					if (loginUsuarioEntity.tfaPasado == true) {
						val intent = Intent(this, ChatsActivity::class.java)
						startActivity(intent)
					} else {
						val intent = Intent(this, A2fActivity::class.java)
						startActivity(intent)
					}
				}


			} catch (e: Exception) {
				Log.v("LoginActivity", e.toString())
				Toasty.error(
					this,
					"Error al iniciar sesión",
					Toasty.LENGTH_SHORT,
					true
				).show()
			}
		}

		btnSingUp.setOnClickListener {
			val intent = Intent(this, RegisterActivity::class.java)
			startActivity(intent)
		}
	}

	fun checkAccount() {
		val loginUsuario = db?.loginUsuarioDao()?.getLoginUsuario()
		if (loginUsuario != null) {
			if (loginUsuario.tfaPasado == true) {
				val intent = Intent(this, ChatsActivity::class.java)
				startActivity(intent)
			} else {
				val intent = Intent(this, A2fActivity::class.java)
				startActivity(intent)
			}
		}
	}

	@SuppressLint("SimpleDateFormat")
	fun saveAccount(jsonString: String): LoginUsuarioEntity {
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