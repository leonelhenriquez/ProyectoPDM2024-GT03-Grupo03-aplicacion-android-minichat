package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_lectura_mensaje",
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_usuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = MensajeEntity::class,
			parentColumns = ["id"],
			childColumns = ["id_mensaje"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class LecturaMensajeEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "leido")
	var leido: Boolean? = null

	@ColumnInfo(name = "id_usuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "id_mensaje")
	var idMensaje: Long? = null

	@ColumnInfo(name = "fecha_hora")
	var fechaHora: Date? = null

	@ColumnInfo(name = "updated_at")
	var updatedAt: Date? = null

	constructor(
		leido: Boolean?,
		idUsuario: Long?,
		idMensaje: Long?,
		fechaHora: Date?,
		updatedAt: Date?
	) : this() {
		this.leido = leido
		this.idUsuario = idUsuario
		this.idMensaje = idMensaje
		this.fechaHora = fechaHora
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		leido: Boolean?,
		idUsuario: Long?,
		idMensaje: Long?,
		fechaHora: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.leido = leido
		this.idUsuario = idUsuario
		this.idMensaje = idMensaje
		this.fechaHora = fechaHora
		this.updatedAt = updatedAt
	}
}