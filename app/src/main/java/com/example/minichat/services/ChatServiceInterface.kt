package com.example.minichat.services

interface ChatServiceInterface {

	fun sendMessage(message: String)

	fun observeNewMessage()

	fun observeNewReaction()

	fun observeJoinedRoom()

}