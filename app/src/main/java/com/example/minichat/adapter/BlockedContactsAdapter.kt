package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.BlockedContacts
import com.example.minichat.R

class BlockedContactsAdapter(
	private val blockedContactsList: List<BlockedContacts>,
	private val onClickListener: (BlockedContacts) -> Unit
) : RecyclerView.Adapter<BlockedContactsViewHolder>() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BlockedContactsViewHolder {
		val layoutInflater = LayoutInflater.from(parent.context)
		return BlockedContactsViewHolder(
			layoutInflater.inflate(
				R.layout.item_blocked_contacts,
				parent,
				false
			)
		)
	}
	
	override fun onBindViewHolder(holder: BlockedContactsViewHolder, position: Int) {
		val item = blockedContactsList[position]
		holder.render(item, onClickListener)
	}

	override fun getItemCount(): Int = blockedContactsList.size
}