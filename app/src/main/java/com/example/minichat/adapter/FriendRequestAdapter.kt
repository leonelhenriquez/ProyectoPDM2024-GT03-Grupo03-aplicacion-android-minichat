package com.example.minichat.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.minichat.R
import com.example.minichat.dataclasses.FriendRequest

class FriendRequestAdapter(
  private val friendRequestList: List<FriendRequest>,
  private val onClickListener: (FriendRequest) -> Unit
) : RecyclerView.Adapter<FriendRequestViewHolder>() {
  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendRequestViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
    return FriendRequestViewHolder(
      layoutInflater.inflate(
        R.layout.item_friend_request,
        parent,
        false
      )
    )
  }

  override fun onBindViewHolder(holder: FriendRequestViewHolder, position: Int) {
    val item = friendRequestList[position]
    holder.render(item, onClickListener)
  }

  override fun getItemCount(): Int = friendRequestList.size
}