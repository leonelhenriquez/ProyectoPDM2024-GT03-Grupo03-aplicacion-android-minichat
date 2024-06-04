package com.example.minichat.dataclasses

data class PerfilFriendRequest(
  val nombre: String,
  val foto: String?
)

data class UsuarioFriendRequest(
  val id: Long,
  val nombre: String,
  val perfil: PerfilFriendRequest
)

data class FriendRequest(
  val id: Long,
  val aceptado: Boolean,
  val usuario: UsuarioFriendRequest
)
