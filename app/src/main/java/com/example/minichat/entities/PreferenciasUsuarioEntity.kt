package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_preferencias_usuario",
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
class PreferenciasUsuarioEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "temaOscuro")
	var temaOscuro: Boolean? = null

	@ColumnInfo(name = "idFuente")
	var idFuente: Long? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(temaOscuro: Boolean?, idFuente: Long?, idUsuario: Long?, createdAt: Date?, updatedAt: Date?) {
		this.temaOscuro = temaOscuro
		this.idFuente = idFuente
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		temaOscuro: Boolean?,
		idFuente: Long?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.temaOscuro = temaOscuro
		this.idFuente = idFuente
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}