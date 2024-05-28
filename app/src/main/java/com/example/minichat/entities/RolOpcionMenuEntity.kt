package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_rol_opcion_menu",
	foreignKeys = [
		ForeignKey(
			entity = OpcionMenuEntity::class,
			parentColumns = ["id"],
			childColumns = ["idOpcionMenu"],
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
class RolOpcionMenuEntity : Serializable {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "idOpcionMenu")
	var idOpcionMenu: Long? = null

	@ColumnInfo(name = "idRol")
	var idRol: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(idOpcionMenu: Long?, idRol: Long?, createdAt: Date?, updatedAt: Date?) {
		this.idOpcionMenu = idOpcionMenu
		this.idRol = idRol
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		idOpcionMenu: Long?,
		idRol: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) {
		this.id = id
		this.idOpcionMenu = idOpcionMenu
		this.idRol = idRol
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}