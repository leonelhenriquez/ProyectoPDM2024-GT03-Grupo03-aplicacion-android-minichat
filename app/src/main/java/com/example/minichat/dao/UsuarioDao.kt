package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.UsuarioEntity
import com.example.minichat.entities.UsuarioPerfil

@Dao
interface UsuarioDao : GenericDAO<UsuarioEntity> {
  @Query("SELECT * FROM mnt_usuario")
  fun getUsuarios(): List<UsuarioEntity>

  @Query("SELECT * FROM mnt_usuario WHERE id = :id")
  fun getUsuarioById(id: Long): UsuarioPerfil?
}