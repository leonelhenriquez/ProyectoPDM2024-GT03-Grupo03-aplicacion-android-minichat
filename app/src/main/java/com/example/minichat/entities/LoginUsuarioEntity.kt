package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.minichat.Commons.GenericEntity

@Entity(
	tableName = LoginUsuarioEntity.TABLE_NAME,
	foreignKeys = [
		ForeignKey(
			entity = UsuarioEntity::class,
			parentColumns = ["id"],
			childColumns = ["idUsuario"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		),
		ForeignKey(
			entity = DispositivoVinculadoEntity::class,
			parentColumns = ["id"],
			childColumns = ["idDispositivo"],
			onDelete = ForeignKey.CASCADE,
			onUpdate = ForeignKey.CASCADE,
		)
	]
)
class LoginUsuarioEntity() : GenericEntity() {

	companion object {
		const val TABLE_NAME = "mnt_login_usuario"
	}

	@PrimaryKey()
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "idUsuario")
	var idUsuario: Long? = null

	@ColumnInfo(name = "idDispositivo")
	var idDispositivo: Long? = null

	@ColumnInfo(name = "token")
	var token: String? = null

	@ColumnInfo(name = "tfaRequerido")
	var tfaRequerido: Boolean? = null

	@ColumnInfo(name = "tfaPasado")
	var tfaPasado: Boolean? = null


	constructor(
		idUsuario: Long?,
		idDispositivo: Long?,
		token: String?,
		tfaRequerido: Boolean?,
		tfaPasado: Boolean?
	) : this() {
		this.idUsuario = idUsuario
		this.idDispositivo = idDispositivo
		this.token = token
		this.tfaRequerido = tfaRequerido
		this.tfaPasado = tfaPasado
	}

	constructor(
		id: Long?,
		idUsuario: Long?,
		idDispositivo: Long?,
		token: String?,
		tfaRequerido: Boolean?,
		tfaPasado: Boolean?
	) : this() {
		this.id = id
		this.idUsuario = idUsuario
		this.idDispositivo = idDispositivo
		this.token = token
		this.tfaRequerido = tfaRequerido
		this.tfaPasado = tfaPasado
	}
}

data class LoginUsuarioData(
	@Embedded
	var loginUsuarioEntity: LoginUsuarioEntity? = null,

	@Relation(
		parentColumn = "idUsuario",
		entityColumn = "id"
	)
	var usuarioPerfil: UsuarioPerfil? = null,
)