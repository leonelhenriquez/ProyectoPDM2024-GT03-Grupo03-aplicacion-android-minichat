package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_dispositivo_vinculado",
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
class DispositivoVinculadoEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombreDispositivo")
	var nombreDispositivo: String? = null

	@ColumnInfo(name = "token")
	var token: String? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "fecha")
	var fecha: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null


	constructor(
		nombreDispositivo: String?,
		token: String?,
		idUsuario: Long?,
		fecha: Date?,
		updatedAt: Date?
	) : this() {
		this.nombreDispositivo = nombreDispositivo
		this.token = token
		this.idUsuario = idUsuario
		this.fecha = fecha
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		nombreDispositivo: String?,
		token: String?,
		idUsuario: Long?,
		fecha: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.nombreDispositivo = nombreDispositivo
		this.token = token
		this.idUsuario = idUsuario
		this.fecha = fecha
		this.updatedAt = updatedAt
	}
}