package com.example.minichat.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.databinding.ItemContactBinding
import com.example.minichat.dataclasses.ContactFind
import com.example.minichat.entities.LoginUsuarioData

class ContactsFindViewHolder(
  view: View
) : RecyclerView.ViewHolder(view) {
  val binding = ItemContactBinding.bind(view)

  fun render(
    contactFind: ContactFind,
    loginUsuario: LoginUsuarioData?,
    onAddFriend: (ContactFind) -> Unit
  ) {
    binding.textViewName.text = contactFind.perfil.nombre
    binding.textViewUsername.text = "@${contactFind.nombre}"


    if (contactFind.contacto_aceptado) {
      binding.buttonSendRequest.isEnabled = false
      binding.buttonSendRequest.visibility = View.GONE
    }

    binding.buttonSendRequest.setOnClickListener {
      onAddFriend(contactFind)
    }
  }
}