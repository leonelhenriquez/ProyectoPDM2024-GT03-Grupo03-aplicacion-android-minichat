package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.BlockedContactsAdapter
import com.example.minichat.adapter.CreateGroupAdapter
import com.example.minichat.databinding.ActivityBlockedContactsBinding
import com.example.minichat.databinding.ActivityCreateGroupBinding

class CreateGroupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreateGroupBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateGroupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val toolbar : Toolbar = findViewById(R.id.toolbarCreateGroup)
        setSupportActionBar(toolbar)
        getSupportActionBar()?.setDisplayHomeAsUpEnabled(true)
        getSupportActionBar()?.setDisplayShowHomeEnabled(true)
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

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
//        val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerCreateGroup.layoutManager = manager
        binding.recyclerCreateGroup.adapter = CreateGroupAdapter(CreateGroupProvider.createGroupList) { createGroup ->
            onItemSelected(
                createGroup
            )
        }
//        binding.recyclerBlockedContacts.addItemDecoration(decoration)
    }

    fun onItemSelected(createGroup: CreateGroup) {
        Toast.makeText(this, "Agregado", Toast.LENGTH_SHORT).show()
    }
}