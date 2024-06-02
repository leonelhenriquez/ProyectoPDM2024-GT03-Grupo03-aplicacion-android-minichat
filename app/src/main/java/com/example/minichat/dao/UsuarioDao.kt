package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.UsuarioEntity

@Dao
interface UsuarioDao : GenericDAO<UsuarioEntity> {
	@Query("SELECT * FROM mnt_usuario")
	fun getUsuarios(): List<UsuarioEntity>
}