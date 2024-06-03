package com.example.minichat.services

import com.example.minichat.entities.MensajeEntity

interface ChatServiceInterface : ObserversWebsocket {
  fun sendMessage(message: MensajeEntity)

}