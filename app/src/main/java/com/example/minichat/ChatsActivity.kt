package com.example.minichat

import android.annotation.SuppressLint
import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.ChatsAdapter
import com.example.minichat.adapter.ContactsFIndAdapter
import com.example.minichat.database.AppDatabase
import com.example.minichat.databinding.ActivityChatsBinding
import com.example.minichat.dataclasses.ContactFind
import com.example.minichat.datasource.RestDataSourceFindContacts
import com.example.minichat.entities.ChatWithData
import com.example.minichat.services.ChatService
import com.example.minichat.services.ObserversWebsocketImpl

@Suppress("DEPRECATION")
class ChatsActivity : AppCompatActivity() {
  private lateinit var binding: ActivityChatsBinding
  private var manager: LinearLayoutManager = LinearLayoutManager(this)
  private var managerContacts: LinearLayoutManager = LinearLayoutManager(this)
  private val db = AppDatabase.getDatabase(this)

  private val handler = Handler(Looper.getMainLooper())
  private var isBound = false
  private var chatService: ChatService? = null


  private val observerWebsocket = object : ObserversWebsocketImpl() {
    override fun observeNewMessage() {
      runOnUiThread {
        loadChats()
      }
    }

    override fun observeNewReaction() {
      runOnUiThread {
        loadChats()
      }
    }

    override fun observeJoinedRoom() {
      runOnUiThread {
        loadChats()
      }
    }

    @SuppressLint("SetTextI18n")
    override fun observeNewFriendRequest(count: Int) {
      runOnUiThread {
        binding.txtTrayRequest.text = "Bandeja de solicitdes (${count})"
      }
    }
  }

  private val connection = object : ServiceConnection {
    override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
      val binder = service as ChatService.ChatServiceBinder
      chatService = binder.getService()
      binder.setObserversWebsocket(observerWebsocket)
      isBound = true
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      isBound = false
    }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityChatsBinding.inflate(layoutInflater)
    setContentView(binding.root)
    setSupportActionBar(binding.toolbar)
    startService(Intent(this, ChatService::class.java))

    binding.recyclerChats.layoutManager = manager
    binding.recyclerContacts.layoutManager = managerContacts

    val userLogin = db.loginUsuarioDao().getLoginUsuario()

    binding.textViewNameProfile.text = userLogin?.usuarioPerfil?.perfil?.nombre

    //this.initRecyclerView(ArrayList<ChatWithData>())


    binding.txtTrayRequest.setOnClickListener {
      val intent = Intent(this, FriendRequestActivity::class.java)
      startActivity(intent)
    }

    //val toolbar: Toolbar = findViewById(R.id.toolbar)

    Intent(this, ChatService::class.java).also { intent ->
      bindService(intent, connection, BIND_AUTO_CREATE)
    }

    loadChats()

    binding.searchView.editText.addTextChangedListener(object : TextWatcher {
      override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
      }

      override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        loadContacts(s.toString()) {
          initRecyclerViewContacts(it)
        }
      }

      override fun afterTextChanged(s: Editable?) {
      }
    })

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

  private fun initRecyclerViewContacts(contacts: List<ContactFind>) {
    val loginUsuarioEntity = db.loginUsuarioDao().getLoginUsuario()
    binding.recyclerContacts.adapter =
      ContactsFIndAdapter(contacts, loginUsuarioEntity, ::onAddFriend)
  }

  private fun onAddFriend(contactFriend: ContactFind) {
    val loginUsuarioEntity = db.loginUsuarioDao().getLoginUsuario()
    loginUsuarioEntity?.usuarioPerfil?.usuario?.id?.let {
      chatService?.sendFriendRequest(
        it,
        contactFriend.id
      )
      binding.searchView.hide()
    }

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

  override fun onDestroy() {
    super.onDestroy()
    if (isBound) {
      unbindService(connection)
      isBound = false
    }
  }

  override fun onResume() {
    super.onResume()
    loadChats()
    chatService?.countFriendRequests()
  }

  private val loadChatsRunnable = object : Runnable {
    override fun run() {
      loadChats()
      chatService?.loadData()
      handler.postDelayed(this, 1000 * 5) // Vuelve a ejecutar después de N segundos
    }
  }

  private fun loadContacts(searchParam: String?, callback: (List<ContactFind>) -> Unit) {
    val loginUsuario = db.loginUsuarioDao().getLoginUsuario()
    RestDataSourceFindContacts.findContacts(
      this,
      loginUsuario?.usuarioPerfil?.usuario?.id,
      loginUsuario?.loginUsuarioEntity?.token
    ) { contacts ->
      var search = searchParam?.lowercase() ?: ""
      val listContacts = contacts.filter {
        it.nombre.contains(
          search,
          true
        ) || it.perfil.nombre.contains(search, true)
      }

      callback(listContacts)
    }
  }

  override fun onBackPressed() {
    Log.d("onBackPressed", "onBackPressed")
    Log.d("onBackPressed", "isCollapsing: ${binding.searchView.isShowing}")
    Log.d("onBackPressed", "isCollapsing: ${binding.searchBar.isCollapsing}")
    if (binding.searchView.isShowing) {
      binding.searchView.hide()
    } else {
      super.onBackPressed()
      finishAffinity()
    }
  }

}