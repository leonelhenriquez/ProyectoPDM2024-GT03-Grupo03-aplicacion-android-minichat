package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(tableName = "ctl_rol")
class RolEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "descripcion")
	var descripcion: String? = null


	constructor(
		nombre: String?,
		descripcion: String?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.nombre = nombre
		this.descripcion = descripcion
	}

	constructor(
		id: Long?,
		nombre: String?,
		descripcion: String?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.nombre = nombre
		this.descripcion = descripcion
	}
}