package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import com.example.minichat.database.AppDatabase
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class SettingActivity : AppCompatActivity() {
    private val db = AppDatabase.getDatabase(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        val txt_dispositivo : TextView = findViewById(R.id.txt_dispositivo)
        val loginUsuarioData = db.loginUsuarioDao().getLoginUsuario()
        val dispositivoVinculado = db.dispositivoVinculadoDao().getDispositivoVinculado(loginUsuarioData?.usuarioPerfil?.usuario?.id!!)
        txt_dispositivo.text = dispositivoVinculado?.nombreDispositivo

        val toolbar : Toolbar = findViewById(R.id.toolbarSetting)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)

        val txt_fechaHoy : TextView = findViewById(R.id.txt_dispositivoFecha)
        val fechaCorta = obtenerFechaCorta()
        txt_fechaHoy.text = fechaCorta
    }
    fun obtenerFechaCorta(): String {
        val fechaActual = LocalDate.now()
        val formato = DateTimeFormatter.ofPattern("d/M/yy")
        return fechaActual.format(formato)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.itemProfile -> Profile()
            R.id.itemSetting -> Setting()
            R.id.itemCreateGroup -> CreateGroup()
            R.id.itemBlockedContact -> BlockedContact()
            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun Profile() {
        Toast.makeText(this, "Perfil", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ProfileActivity::class.java)
        startActivity(intent)
    }

    fun Setting() {
        Toast.makeText(this, "Configuración", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, SettingActivity::class.java)
        startActivity(intent)
    }

    fun CreateGroup() {
        Toast.makeText(this, "Crear Grupo", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, CreateGroupActivity::class.java)
        startActivity(intent)
    }

    fun BlockedContact  () {
        Toast.makeText(this, "Contactos Bloqueados", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, BlockedContactsActivity::class.java)
        startActivity(intent)
    }
}