package com.example.minichat.services

interface ObserversWebsocket {
  fun observeNewMessage()

  fun observeNewReaction()

  fun observeJoinedRoom()

  fun observeNewFriendRequest(count: Int)
}