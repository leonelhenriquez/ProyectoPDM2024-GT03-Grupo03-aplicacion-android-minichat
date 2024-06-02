package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_contacto_usuario",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_usuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_contacto"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class ContactoUsuarioEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "aceptado")
	var aceptado: Boolean? = null

	@ColumnInfo(name = "id_usuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "id_contacto")
	var idContacto: Long? = null

	@ColumnInfo(name = "created_at")
	var createdAt: Date? = null

	@ColumnInfo(name = "updated_at")
	var updatedAt: Date? = null

	constructor(
		aceptado: Boolean?,
		idUsuario: Long?,
		idContacto: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.aceptado = aceptado
		this.idUsuario = idUsuario
		this.idContacto = idContacto
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		aceptado: Boolean?,
		idUsuario: Long?,
		idContacto: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.aceptado = aceptado
		this.idUsuario = idUsuario
		this.idContacto = idContacto
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}