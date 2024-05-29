package com.example.minichat.dao

import androidx.room.Dao
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.PreferenciaChatEntity

@Dao
interface PreferenciaChatDao : GenericDAO<PreferenciaChatEntity> {
}