package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_reaccion_mensaje",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUsuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = MensajeEntity::class,
			parentColumns = ["id"],
			childColumns = ["idMensaje"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = ReaccionEntity::class,
			parentColumns = ["id"],
			childColumns = ["idReaccion"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class ReaccionMensajeEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "idMensaje")
	var idMensaje: Long? = null

	@ColumnInfo(name = "idReaccion")
	var idReaccion: Long? = null

	@ColumnInfo(name = "fechaReaccion")
	var fechaReaccion: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(idUsuario: Long?, idMensaje: Long?, idReaccion: Long?, fechaReaccion: Date?, updatedAt: Date?) {
		this.idUsuario = idUsuario
		this.idMensaje = idMensaje
		this.idReaccion = idReaccion
		this.fechaReaccion = fechaReaccion
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		idUsuario: Long?,
		idMensaje: Long?,
		idReaccion: Long?,
		fechaReaccion: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.idUsuario = idUsuario
		this.idMensaje = idMensaje
		this.idReaccion = idReaccion
		this.fechaReaccion = fechaReaccion
		this.updatedAt = updatedAt
	}
}