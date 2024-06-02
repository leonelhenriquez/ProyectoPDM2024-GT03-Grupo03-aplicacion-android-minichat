package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity

@Entity(tableName = "ctl_tipo_chat")
class TipoChatEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	constructor(id: Long?, nombre: String?) : this() {
		this.id = id
		this.nombre = nombre
	}
}