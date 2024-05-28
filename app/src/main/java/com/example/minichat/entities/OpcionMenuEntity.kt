package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "ctl_opcion_menu")
class OpcionMenuEntity  : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "descripcion")
	var descripcion: String? = null

	constructor(id: Long?, descripcion: String?) {
		this.id = id
		this.descripcion = descripcion
	}
}