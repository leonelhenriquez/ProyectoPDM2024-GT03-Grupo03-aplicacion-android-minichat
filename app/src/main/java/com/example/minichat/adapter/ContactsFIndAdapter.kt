package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.R
import com.example.minichat.dataclasses.ContactFind
import com.example.minichat.entities.LoginUsuarioData

class ContactsFIndAdapter(
  private val contactsList: List<ContactFind>,
  private val loginUsuarioData: LoginUsuarioData?,
  private val onAddFriend: (ContactFind) -> Unit
) : RecyclerView.Adapter<ContactsFindViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsFindViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return ContactsFindViewHolder(
      layoutInflater.inflate(
        R.layout.item_contact,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: ContactsFindViewHolder, position: Int) {
    val item = contactsList[position]
    holder.render(item, loginUsuarioData, onAddFriend)
  }

  override fun getItemCount(): Int {
    return contactsList.size
  }
}