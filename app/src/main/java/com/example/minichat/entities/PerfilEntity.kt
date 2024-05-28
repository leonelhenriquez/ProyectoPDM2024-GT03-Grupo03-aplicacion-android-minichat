package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_perfil",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUsuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class PerfilEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "biografia")
	var biografia: String? = null

	@ColumnInfo(name = "foto")
	var foto: String? = null

	@ColumnInfo(name = "correo")
	var correo: String? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(
		nombre: String?,
		biografia: String?,
		foto: String?,
		correo: String?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.nombre = nombre
		this.biografia = biografia
		this.foto = foto
		this.correo = correo
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		nombre: String?,
		biografia: String?,
		foto: String?,
		correo: String?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.nombre = nombre
		this.biografia = biografia
		this.foto = foto
		this.correo = correo
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}


}