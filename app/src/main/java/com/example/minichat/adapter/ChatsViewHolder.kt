package com.example.minichat.adapter

import android.content.Intent
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichat.ChatActivity
import com.example.minichat.databinding.ItemChatBinding
import com.example.minichat.entities.ChatWithData
import com.example.minichat.entities.LoginUsuarioData
import com.example.minichat.entities.UsuarioEntity

class ChatsViewHolder(
  view: View
) : RecyclerView.ViewHolder(view) {
  val binding = ItemChatBinding.bind(view)


  fun render(chatdata: ChatWithData, loginUsuario: LoginUsuarioData?) {

    var usuario: UsuarioEntity? = null
    for (usuarioChat in chatdata.usuariosChatList) {
      if (usuarioChat.id != loginUsuario?.usuarioPerfil?.usuario?.id) {
        usuario = usuarioChat
        binding.textViewNameChat.text = usuarioChat.nombre
      }
    }

    if (chatdata.chat.uriFoto != null) {
      Glide.with(binding.imageViewProfileUser.context).load(chatdata.chat.uriFoto)
        .into(binding.imageViewProfileUser)
    }

    if (chatdata.mensaje != null) {
      binding.txtLastMessage.text = chatdata.mensaje?.mensaje ?: ""
    }

    Log.d("ChatListLog", chatdata.toString())
    //binding.textViewNameChat.text = chatdata.usuariosChatList
    //binding.txtLastMessage.text = chatEntity.lastMessage
    binding.root.setOnClickListener {
      val intent = Intent(binding.root.context, ChatActivity::class.java)
      intent.putExtra("chatId", chatdata.chat.id)
      intent.putExtra("usuarioId", usuario?.id)
      binding.root.context.startActivity(intent)
    }
  }
}