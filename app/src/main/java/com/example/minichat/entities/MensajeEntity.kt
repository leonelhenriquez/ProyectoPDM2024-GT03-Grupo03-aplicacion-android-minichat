package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_mensaje",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_usuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = ChatEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_chat"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class MensajeEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "mensaje")
	var mensaje: String? = null

	@ColumnInfo(name = "fecha_hora")
	var fechaHora: Date? = null

	@ColumnInfo(name = "id_usuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "id_chat")
	var idChat: Long? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null


	constructor(
		mensaje: String?,
		fechaHora: Date?,
		idUsuario: Long?,
		idChat: Long?,
		updatedAt: Date?
	) : this() {
		this.mensaje = mensaje
		this.fechaHora = fechaHora
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		mensaje: String?,
		fechaHora: Date?,
		idUsuario: Long?,
		idChat: Long?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.mensaje = mensaje
		this.fechaHora = fechaHora
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.updatedAt = updatedAt
	}


}