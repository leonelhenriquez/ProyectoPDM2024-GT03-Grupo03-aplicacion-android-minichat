package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.adapter.BlockedContactsAdapter
import com.example.minichat.databinding.ActivityBlockedContactsBinding

class BlockedContactsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBlockedContactsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBlockedContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val btnBack : ImageButton = findViewById(R.id.imbBackB)

        btnBack.setOnClickListener() {
            val intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
//        val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerBlockedContacts.layoutManager = manager
        binding.recyclerBlockedContacts.adapter = BlockedContactsAdapter(BlockedContactsProvider.blockedContactList) { blockedContacts ->
            onItemSelected(
                blockedContacts
            )
        }
//        binding.recyclerBlockedContacts.addItemDecoration(decoration)
    }

    fun onItemSelected(blockedContacts: BlockedContacts) {
        Toast.makeText(this, "Desbloqueado", Toast.LENGTH_SHORT).show()
    }
}