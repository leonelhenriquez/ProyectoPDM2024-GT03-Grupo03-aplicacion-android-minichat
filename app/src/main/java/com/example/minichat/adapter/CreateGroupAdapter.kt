package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.CreateGroup
import com.example.minichat.R

class CreateGroupAdapter(private val createGroupList: List<CreateGroup>, private val onClickListener: (CreateGroup) -> Unit) : RecyclerView.Adapter<CreateGroupViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CreateGroupViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return CreateGroupViewHolder(layoutInflater.inflate(R.layout.item_create_group, parent, false))
    }

    override fun onBindViewHolder(holder: CreateGroupViewHolder, position: Int) {
        val item = createGroupList[position]
        holder.render(item, onClickListener)
    }

    override fun getItemCount(): Int = createGroupList.size
}