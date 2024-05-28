package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_chat",
	foreignKeys = [ForeignKey(
		entity = TipoChatEntity::class,
		parentColumns = ["id"],
		childColumns = ["idTipoChat"],
		onDelete = ForeignKey.CASCADE,
		onUpdate = ForeignKey.CASCADE
	)]
)
class ChatEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "uriFoto")
	var uriFoto: String? = null

	@ColumnInfo(name = "idTipoChat")
	var idTipoChat: Long? = null

	@ColumnInfo(name = "fechaCreacion")
	var fechaCreacion: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(uriFoto: String?, idTipoChat: Long?, fechaCreacion: Date?, updatedAt: Date?) {
		this.uriFoto = uriFoto
		this.idTipoChat = idTipoChat
		this.fechaCreacion = fechaCreacion
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		uriFoto: String?,
		idTipoChat: Long?,
		fechaCreacion: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.uriFoto = uriFoto
		this.idTipoChat = idTipoChat
		this.fechaCreacion = fechaCreacion
		this.updatedAt = updatedAt
	}


}