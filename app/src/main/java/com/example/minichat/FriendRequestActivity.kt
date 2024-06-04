package com.example.minichat

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.FriendRequestAdapter
import com.example.minichat.database.AppDatabase
import com.example.minichat.databinding.ActivityFriendRequestBinding
import com.example.minichat.dataclasses.FriendRequest
import com.example.minichat.datasource.RestDataSourceFriendRequest

class FriendRequestActivity : AppCompatActivity() {
  private lateinit var binding: ActivityFriendRequestBinding
  private val db = AppDatabase.getDatabase(this)
  private val manager = LinearLayoutManager(this)
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityFriendRequestBinding.inflate(layoutInflater)
    setContentView(binding.root)
    initRecyclerView()

    setSupportActionBar(binding.toolbarFriendRequest)
    supportActionBar?.setDisplayHomeAsUpEnabled(true)
    supportActionBar?.setDisplayShowHomeEnabled(true)

    binding.recyclerFriendRequest.layoutManager = manager
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

  fun BlockedContact() {
    Toast.makeText(this, "Contactos Bloqueados", Toast.LENGTH_SHORT).show()
    val intent = Intent(this, BlockedContactsActivity::class.java)
    startActivity(intent)
  }

  private fun initRecyclerView() {


    val userLogin = db.loginUsuarioDao().getLoginUsuario()
    RestDataSourceFriendRequest.friendRequests(
      userLogin?.usuarioPerfil?.usuario?.id,
      userLogin?.loginUsuarioEntity?.token,
      this
    ) { friendRequestList ->
      binding.recyclerFriendRequest.adapter =
        FriendRequestAdapter(friendRequestList) { friendRequest ->
          onItemSelected(
            friendRequest
          )
        }
    }

  }

  fun onItemSelected(friendRequest: FriendRequest) {
    val userLogin = db.loginUsuarioDao().getLoginUsuario()

    RestDataSourceFriendRequest.acceptFriendRequest(
      userLogin?.usuarioPerfil?.usuario?.id,
      friendRequest.usuario.id,
      userLogin?.loginUsuarioEntity?.token,
      this
    ) {
      initRecyclerView()
    }
  }
}