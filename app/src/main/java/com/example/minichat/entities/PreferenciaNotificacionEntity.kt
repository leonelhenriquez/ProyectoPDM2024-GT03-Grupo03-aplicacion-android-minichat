package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
	tableName = "mnt_preferencia_notificacion",
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
class PreferenciaNotificacionEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "silenciadas")
	var silenciadas: Boolean? = null

	@ColumnInfo(name = "activoHorarioNotificacion")
	var activoHorarioNotificacion: Boolean? = null

	@ColumnInfo(name = "horaInicio")
	var horaInicio: Date? = null

	@ColumnInfo(name = "horaFin")
	var horaFin: Date? = null

	@ColumnInfo(name = "fondoColor")
	var fondoColor: String? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null


	constructor(
		silenciadas: Boolean?,
		activoHorarioNotificacion: Boolean?,
		horaInicio: Date?,
		horaFin: Date?,
		fondoColor: String?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.silenciadas = silenciadas
		this.activoHorarioNotificacion = activoHorarioNotificacion
		this.horaInicio = horaInicio
		this.horaFin = horaFin
		this.fondoColor = fondoColor
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(
		id: Long?,
		silenciadas: Boolean?,
		activoHorarioNotificacion: Boolean?,
		horaInicio: Date?,
		horaFin: Date?,
		fondoColor: String?,
		idUsuario: Long?,
		createdAt: Date?,
		updatedAt: Date?
	) : this() {
		this.id = id
		this.silenciadas = silenciadas
		this.activoHorarioNotificacion = activoHorarioNotificacion
		this.horaInicio = horaInicio
		this.horaFin = horaFin
		this.fondoColor = fondoColor
		this.idUsuario = idUsuario
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}
}