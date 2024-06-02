package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.UsuarioChatEntity

@Dao
interface UsuarioChatDao : GenericDAO<UsuarioChatEntity> {

	@Query("SELECT * FROM mnt_usuario_chat WHERE idUsuario = :idUsuario AND idChat = :idChat")
	fun getUsuarioChat(idUsuario: Long?, idChat: Long): UsuarioChatEntity?
}