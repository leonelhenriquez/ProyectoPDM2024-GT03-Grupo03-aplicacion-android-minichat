package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichat.BlockedContacts
import com.example.minichat.Chats
import com.example.minichat.databinding.ItemBlockedContactsBinding
import com.example.minichat.databinding.ItemChatsBinding

class ChatsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemChatsBinding.bind(view)

    fun render(chatsModel: Chats, onClickListener:(Chats) -> Unit) {
        Glide.with(binding.imgChats.context).load(chatsModel.imgChats).into(binding.imgChats)
        binding.txtNameChats.text = chatsModel.nameChats
        binding.txtMessageChats.text = chatsModel.messageChats
        binding.txtRequest.text = chatsModel.accionChats
        binding.txtRequest.setOnClickListener { onClickListener(chatsModel) }
        // <!-- con itemView podria tomar todo el item Toast.makeText(binding.imgContactB.context, "Bloqueado", Toast.LENGTH_SHORT).show() -->
    }
}