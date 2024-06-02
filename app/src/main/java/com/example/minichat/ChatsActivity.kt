package com.example.minichat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.ChatsAdapter
import com.example.minichat.database.AppDatabase
import com.example.minichat.databinding.ActivityChatsBinding
import com.example.minichat.entities.ChatWithData
import com.example.minichat.services.ChatService

class ChatsActivity : AppCompatActivity() {
	private lateinit var binding: ActivityChatsBinding
	private var manager: LinearLayoutManager? = null
	private val db = AppDatabase.getDatabase(this)
	private val handler = Handler(Looper.getMainLooper())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityChatsBinding.inflate(layoutInflater)
		setContentView(binding.root)
		startService(Intent(this, ChatService::class.java))

		manager = LinearLayoutManager(this)
		binding.recyclerChats.layoutManager = manager

		val userLogin = db.loginUsuarioDao().getLoginUsuario()

		binding.textViewNameProfile.text = userLogin?.usuarioPerfil?.usuario?.nombre

		//this.initRecyclerView(ArrayList<ChatWithData>())
		loadChats()


		val txtTrayRequest: TextView = findViewById(R.id.txtTrayRequest)
		txtTrayRequest.setOnClickListener {
			val intent = Intent(this, FriendRequestActivity::class.java)
			startActivity(intent)
		}

		val toolbar: Toolbar = findViewById(R.id.toolbar)
		setSupportActionBar(toolbar)

	}

	private fun loadChats() {
		val listChats = db.chatDao().all()

		val arrayListChats = ArrayList<ChatWithData>()

		for (chat in (listChats ?: listOf()).iterator()) {
			arrayListChats.add(chat)
		}

		initRecyclerView(arrayListChats)
	}

	private fun initRecyclerView(chatList: ArrayList<ChatWithData>) {

		val loginUsuarioEntity = db.loginUsuarioDao().getLoginUsuario()
		for (chat in chatList) {
			Log.d("ChatListLog", chat.toString())
		}
		binding.recyclerChats.adapter = ChatsAdapter(chatList, loginUsuarioEntity)

	}

	override fun onCreateOptionsMenu(menu: Menu?): Boolean {
		menuInflater.inflate(R.menu.menu, menu)
		return super.onCreateOptionsMenu(menu)
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		when (item.itemId) {
			R.id.itemProfile -> Profile()
			R.id.itemSetting -> Setting()
			R.id.itemCreateGroup -> CreateGroup()
			R.id.itemBlockedContact -> BlockedContact()
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

	fun BlockedContact() {
		Toast.makeText(this, "Contactos Bloqueados", Toast.LENGTH_SHORT).show()
		val intent = Intent(this, BlockedContactsActivity::class.java)
		startActivity(intent)
	}

	fun onItemSelected(blockedContacts: Chats) {
		Toast.makeText(this, "Leido", Toast.LENGTH_SHORT).show()
	}

	override fun onResume() {
		super.onResume()
		handler.post(loadChatsRunnable)
	}

	override fun onPause() {
		super.onPause()
		handler.removeCallbacks(loadChatsRunnable)
	}

	private val loadChatsRunnable = object : Runnable {
		override fun run() {
			loadChats()
			handler.postDelayed(this, 1000 * 1) // Vuelve a ejecutar después de N segundos
		}
	}

}