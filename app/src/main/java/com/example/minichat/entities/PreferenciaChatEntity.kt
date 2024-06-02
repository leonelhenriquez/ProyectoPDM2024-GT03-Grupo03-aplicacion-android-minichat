package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_preferencia_chat",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUsuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = ChatEntity::class,
			parentColumns = ["id"],
			childColumns = ["idChat"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class PreferenciaChatEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "fondoColor")
	var fondoColor: String? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "idChat")
	var idChat: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null


	constructor(
		nombre: String?,
		fondoColor: String?,
		idUsuario: Long?,
		idChat: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.nombre = nombre
		this.fondoColor = fondoColor
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		nombre: String?,
		fondoColor: String?,
		idUsuario: Long?,
		idChat: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.nombre = nombre
		this.fondoColor = fondoColor
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}