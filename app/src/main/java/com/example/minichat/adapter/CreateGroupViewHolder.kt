package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.minichat.CreateGroup
import com.example.minichat.databinding.ItemCreateGroupBinding

class CreateGroupViewHolder(view : View) : RecyclerView.ViewHolder(view) {
    val binding = ItemCreateGroupBinding.bind(view)

    fun render(createGroupModel: CreateGroup, onClickListener:(CreateGroup) -> Unit) {
        Glide.with(binding.imgContactCG.context).load(createGroupModel.imgContact).into(binding.imgContactCG)
        binding.txtNameContactCG.text = createGroupModel.nameContact
        binding.txtAdd.text = createGroupModel.accionContact
        binding.txtAdd.setOnClickListener { onClickListener(createGroupModel) }
        // <!-- con itemView podria tomar todo el item Toast.makeText(binding.imgContact.context, "Bloqueado", Toast.LENGTH_SHORT).show() -->
    }
}