package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichat.BlockedContacts
import com.example.minichat.databinding.ItemBlockedContactsBinding

class BlockedContactsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val binding = ItemBlockedContactsBinding.bind(view)

    fun render(blockedContactModel: BlockedContacts, onClickListener:(BlockedContacts) -> Unit) {
        Glide.with(binding.imgContactB.context).load(blockedContactModel.imgContact).into(binding.imgContactB)
        binding.txtNameContactB.text = blockedContactModel.nameContact
        binding.txtBlock.text = blockedContactModel.accionContact
        binding.txtBlock.setOnClickListener { onClickListener(blockedContactModel) }
        // <!-- con itemView podria tomar todo el item Toast.makeText(binding.imgContactB.context, "Bloqueado", Toast.LENGTH_SHORT).show() -->
    }
}