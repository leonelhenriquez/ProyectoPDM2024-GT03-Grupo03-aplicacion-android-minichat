package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_doble_factor_usuario",
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
class DobleFactorUsuarioEntity : Serializable {

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "secret")
	var secret: String? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(secret: String?, idUsuario: Long?, createdAt: Date?, updatedAt: Date?) {
		this.secret = secret
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(id: Long?, secret: String?, idUsuario: Long?, createdAt: Date?, updatedAt: Date?) {
		this.id = id
		this.secret = secret
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}


}