package com.example.minichat.dataclasses

data class Perfil(
  val nombre: String,
  val foto: String?
)

data class ContactFind(
  val id: Long,
  val nombre: String,
  val createdAt: String,
  val updatedAt: String,
  val contacto_aceptado: Boolean,
  val perfil: Perfil
)