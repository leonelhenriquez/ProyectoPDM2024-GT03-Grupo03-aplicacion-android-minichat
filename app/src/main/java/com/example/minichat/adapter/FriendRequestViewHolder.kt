package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichat.FriendRequest
import com.example.minichat.databinding.ItemFriendRequestBinding

class FriendRequestViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemFriendRequestBinding.bind(view)

    fun render(friendRequestModal: FriendRequest, onClickListener:(FriendRequest) -> Unit) {
        Glide.with(binding.imgContactF.context).load(friendRequestModal.imgContact).into(binding.imgContactF)
        binding.txtNameContactF.text = friendRequestModal.nameContact
        binding.txtRequest.text = friendRequestModal.accionContact
        binding.txtRequest.setOnClickListener { onClickListener(friendRequestModal) }
        // <!-- con itemView podria tomar todo el item Toast.makeText(binding.imgContactB.context, "Bloqueado", Toast.LENGTH_SHORT).show() -->
    }
}