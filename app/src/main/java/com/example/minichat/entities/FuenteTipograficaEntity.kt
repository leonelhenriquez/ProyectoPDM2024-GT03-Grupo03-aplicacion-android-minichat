package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity

@Entity(tableName = "ctl_fuente_tipografica")
class FuenteTipograficaEntity() : GenericEntity() {
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