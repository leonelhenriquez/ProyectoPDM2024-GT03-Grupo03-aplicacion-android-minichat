package com.example.minichat.dao

import androidx.room.Dao
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.LoginUsuarioEntity

@Dao
interface LoginUsuarioDao : GenericDAO<LoginUsuarioEntity> {
}