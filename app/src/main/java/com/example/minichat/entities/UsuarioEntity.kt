package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_usuario")
class UsuarioEntity : Serializable{
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "contra")
	var contra: String? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null
}