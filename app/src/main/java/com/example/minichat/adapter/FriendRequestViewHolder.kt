package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.databinding.ItemFriendRequestBinding
import com.example.minichat.dataclasses.FriendRequest

class FriendRequestViewHolder(view: View) : RecyclerView.ViewHolder(view) {
  val binding = ItemFriendRequestBinding.bind(view)

  fun render(friendRequest: FriendRequest, onClickListener: (FriendRequest) -> Unit) {
    //Glide.with(binding.imgContactF.context).load(friendRequestModal.imgContact).into(binding.imgContactF)

    binding.textViewNameFriendRequest.text = friendRequest.usuario.perfil.nombre
    binding.textViewUsernameFriendRequest.text = "@${friendRequest.usuario.nombre}"

    if (friendRequest.aceptado) {
      binding.buttonAcceptFriendRequest.isEnabled = false
      binding.buttonAcceptFriendRequest.visibility = View.GONE
    }

    binding.buttonAcceptFriendRequest.setOnClickListener {
      onClickListener(friendRequest)
    }
  }
}