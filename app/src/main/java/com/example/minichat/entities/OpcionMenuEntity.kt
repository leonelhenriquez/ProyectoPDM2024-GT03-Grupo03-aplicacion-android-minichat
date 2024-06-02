package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity

@Entity(tableName = "ctl_opcion_menu")
class OpcionMenuEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "descripcion")
	var descripcion: String? = null

	constructor(id: Long?, descripcion: String?) : this() {
		this.id = id
		this.descripcion = descripcion
	}
}