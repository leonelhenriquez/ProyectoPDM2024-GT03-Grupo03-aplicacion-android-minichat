package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.LoginUsuarioData
import com.example.minichat.entities.LoginUsuarioEntity

@Dao
interface LoginUsuarioDao : GenericDAO<LoginUsuarioEntity> {
	@Query("DELETE FROM mnt_login_usuario")
	fun nukeTable()

	@Query("SELECT * FROM mnt_login_usuario LIMIT 1")
	fun getLoginUsuario(): LoginUsuarioData?
}
