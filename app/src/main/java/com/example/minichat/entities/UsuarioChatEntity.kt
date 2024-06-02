package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_usuario_chat",
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
		),
		ForeignKey(
			entity = RolEntity::class,
			parentColumns = ["id"],
			childColumns = ["idRol"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class UsuarioChatEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "idChat")
	var idChat: Long? = null

	@ColumnInfo(name = "idRol")
	var idRol: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	/*@Relation(
		parentColumn = "idUsuario",
		entityColumn = "id",
		entity = UsuarioEntity::class
	)
	var usuario: UsuarioEntity? = null*/


	constructor(
		idUsuario: Long?,
		idChat: Long?,
		idRol: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.idRol = idRol
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		idUsuario: Long?,
		idChat: Long?,
		idRol: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.idUsuario = idUsuario
		this.idChat = idChat
		this.idRol = idRol
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}


}