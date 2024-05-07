package com.example.minichat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    private fun initRecyclerView() {
        binding.recyclerBlockedContacts.layoutManager = LinearLayoutManager(this)
        binding.recyclerBlockedContacts.adapter = BlockedContactsAdapter(BlockedContactsProvider.blockedContactList)
    }
}