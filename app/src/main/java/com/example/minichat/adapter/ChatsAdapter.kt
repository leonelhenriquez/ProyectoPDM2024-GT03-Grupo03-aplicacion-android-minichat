package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.Chats
import com.example.minichat.R

class ChatsAdapter(private val chatsList:List<Chats>, private val onClickListener:(Chats) -> Unit) : RecyclerView.Adapter<ChatsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ChatsViewHolder(layoutInflater.inflate(R.layout.item_chats, parent, false))
    }

    override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
        val item = chatsList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = chatsList.size
}