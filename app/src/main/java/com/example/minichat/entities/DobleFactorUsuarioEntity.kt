package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_doble_factor_usuario",
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
class DobleFactorUsuarioEntity() : GenericEntity() {

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


	constructor(secret: String?, idUsuario: Long?, createdAt: Date?, updatedAt: Date?) : this() {
		this.secret = secret
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		secret: String?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.secret = secret
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}


}