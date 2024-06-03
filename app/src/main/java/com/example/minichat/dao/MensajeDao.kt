package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.MensajeEntity
import com.example.minichat.entities.MensajeWithData

@Dao
interface MensajeDao : GenericDAO<MensajeEntity> {
  @Query("SELECT * FROM mnt_mensaje WHERE id_chat = :idChat")
  fun getMensajesChat(idChat: Long): List<MensajeWithData>
}