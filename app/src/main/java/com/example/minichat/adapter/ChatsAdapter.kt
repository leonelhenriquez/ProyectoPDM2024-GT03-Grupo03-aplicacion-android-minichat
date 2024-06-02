package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.R
import com.example.minichat.entities.ChatWithData
import com.example.minichat.entities.LoginUsuarioData

class ChatsAdapter(
	private val chatsList: List<ChatWithData>,
	private val loginUsuarioEntity: LoginUsuarioData?
) : RecyclerView.Adapter<ChatsViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatsViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		return ChatsViewHolder(
			layoutInflater.inflate(
				R.layout.item_chat,
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: ChatsViewHolder, position: Int) {
		val item = chatsList[position]
		holder.render(item, loginUsuarioEntity)
	}

	override fun getItemCount(): Int {
		return chatsList.size
	}
}