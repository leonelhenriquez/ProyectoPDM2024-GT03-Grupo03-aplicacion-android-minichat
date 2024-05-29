package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "ctl_rol")
class RolEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "descripcion")
	var descripcion: String? = null


	constructor()

	constructor(
		nombre: String?,
		descripcion: String?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.nombre = nombre
		this.descripcion = descripcion
	}

	constructor(
		id: Long?,
		nombre: String?,
		descripcion: String?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.nombre = nombre
		this.descripcion = descripcion
	}
}