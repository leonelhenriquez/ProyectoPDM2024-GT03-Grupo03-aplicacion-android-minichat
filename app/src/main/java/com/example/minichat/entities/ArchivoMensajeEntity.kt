package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity

@Entity(
	tableName = ArchivoMensajeEntity.TABLE_NAME,
	foreignKeys = [
		ForeignKey(
			entity = MensajeEntity::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("idMensaje"),
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = TipoArchivoEntity::class,
			parentColumns = arrayOf("id"),
			childColumns = arrayOf("idTipoArchivo"),
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class ArchivoMensajeEntity() : GenericEntity() {

	companion object {
		const val TABLE_NAME = "mnt_archivo_mensaje"
	}

	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "urlArchivo")
	var urlArchivo: String? = null

	@ColumnInfo(name = "idMensaje")
	var idMensaje: Long? = null

	@ColumnInfo(name = "idTipoArchivo")
	var idTipoArchivo: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: String? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: String? = null

	constructor(
		urlArchivo: String?,
		idMensaje: Long?,
		idTipoArchivo: Long?,
		createdAt: String?,
		updatedAt: String?
	) : this() {
		this.urlArchivo = urlArchivo
		this.idMensaje = idMensaje
		this.idTipoArchivo = idTipoArchivo
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		urlArchivo: String?,
		idMensaje: Long?,
		idTipoArchivo: Long?,
		createdAt: String?,
		updatedAt: String?
	) : this() {
		this.id = id
		this.urlArchivo = urlArchivo
		this.idMensaje = idMensaje
		this.idTipoArchivo = idTipoArchivo
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}


}