package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.R
import com.example.minichat.entities.LoginUsuarioData
import com.example.minichat.entities.MensajeWithData

class MessagesAdapter(
  private val messagesList: List<MensajeWithData>,
  private val loginUsuarioEntity: LoginUsuarioData?
) : RecyclerView.Adapter<MessageViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return MessageViewHolder(
      layoutInflater.inflate(
        R.layout.item_message,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
    val item = messagesList[position]
    holder.render(item, loginUsuarioEntity)
  }

  override fun getItemCount(): Int {
    return messagesList.size
  }
}