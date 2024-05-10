package com.example.minichat

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.minichat.adapter.FriendRequestAdapter
import com.example.minichat.databinding.ActivityFriendRequestBinding

class FriendRequestActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFriendRequestBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFriendRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initRecyclerView()

        val btnBack : ImageButton = findViewById(R.id.imbBackF)

        btnBack.setOnClickListener() {
            val intent = Intent(this, ChatsActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val manager = LinearLayoutManager(this)
//        val decoration = DividerItemDecoration(this, manager.orientation)

        binding.recyclerFriendRequest.layoutManager = manager
        binding.recyclerFriendRequest.adapter = FriendRequestAdapter(FriendRequestProvider.friendRequestList) { friendRequest ->
            onItemSelected(
                friendRequest
            )
        }
//        binding.recyclerFriendRequest.addItemDecoration(decoration)
    }

    fun onItemSelected(friendRequest: FriendRequest) {
        Toast.makeText(this, "Desbloqueado", Toast.LENGTH_SHORT).show()
    }
}