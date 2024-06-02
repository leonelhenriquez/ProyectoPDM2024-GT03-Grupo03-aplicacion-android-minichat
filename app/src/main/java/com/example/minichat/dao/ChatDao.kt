package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.ChatEntity
import com.example.minichat.entities.ChatWithData

@Dao
interface ChatDao : GenericDAO<ChatEntity> {
	@Query("SELECT * FROM mnt_chat WHERE id = :id")
	fun getById(id: Long): ChatEntity?

	@Query("SELECT * FROM mnt_chat")
	fun all(): List<ChatWithData>?
}