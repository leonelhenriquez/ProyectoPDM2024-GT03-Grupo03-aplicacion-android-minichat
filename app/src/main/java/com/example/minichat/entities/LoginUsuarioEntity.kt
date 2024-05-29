package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "mnt_login_usuario")
class LoginUsuarioEntity : Serializable{
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "id_usuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "token")
	var token: String? = null
}