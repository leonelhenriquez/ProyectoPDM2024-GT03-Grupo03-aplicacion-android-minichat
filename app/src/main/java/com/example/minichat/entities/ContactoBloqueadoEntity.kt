package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_contacto_bloqueado",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUsuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idContacto"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class ContactoBloqueadoEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "idContacto")
	var idContacto: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(idUsuario: Long?, idContacto: Long?, createdAt: Date?, updatedAt: Date?) {
		this.idUsuario = idUsuario
		this.idContacto = idContacto
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		idUsuario: Long?,
		idContacto: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.idUsuario = idUsuario
		this.idContacto = idContacto
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}