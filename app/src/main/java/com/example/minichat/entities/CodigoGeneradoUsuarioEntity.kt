package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.Date

@Entity(tableName = "mnt_codigo_generado_usuario",
	foreignKeys = [
		ForeignKey(
			entity = DobleFactorUsuarioEntity::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("idDobleFactorUsuario"),
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class CodigoGeneradoUsuarioEntity : Serializable {

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "codigo")
	var codigo: String? = null

	@ColumnInfo(name = "idDobleFactorUsuario")
	var idDobleFactorUsuario: Long? = null

	@ColumnInfo(name = "fechaExpiracion")
	var fechaExpiracion: Date? = null

	@ColumnInfo(name = "fechaCreacion")
	var fechaCreacion: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null

	constructor()

	constructor(codigo: String?, idDobleFactorUsuario: Long?, fechaExpiracion: Date?, fechaCreacion: Date?, updatedAt: Date?) {
		codigo.also { this.codigo = it }
		this.idDobleFactorUsuario = idDobleFactorUsuario
		this.fechaExpiracion = fechaExpiracion
		this.fechaCreacion = fechaCreacion
		this.updatedAt = updatedAt
	}

	constructor(id: Long?, codigo: String?, idDobleFactorUsuario: Long?, fechaExpiracion: Date?, fechaCreacion: Date?, updatedAt: Date?) {
		this.id = id
		this.codigo = codigo
		this.idDobleFactorUsuario = idDobleFactorUsuario
		this.fechaExpiracion = fechaExpiracion
		this.fechaCreacion = fechaCreacion
		this.updatedAt = updatedAt
	}
}