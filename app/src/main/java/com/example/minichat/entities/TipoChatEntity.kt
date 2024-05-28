package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ctl_tipo_chat")
class TipoChatEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null
}