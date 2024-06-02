package com.example.minichat.dao

import androidx.room.Dao
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.ReaccionMensajeEntity

@Dao
interface ReaccionMensajeDao : GenericDAO<ReaccionMensajeEntity> {
}