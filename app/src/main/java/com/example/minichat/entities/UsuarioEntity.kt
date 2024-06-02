package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(tableName = "mnt_usuario")
class UsuarioEntity() : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "nombre")
	var nombre: String? = null

	@ColumnInfo(name = "createdAt")
	var createdAt: Date? = null

	@ColumnInfo(name = "updatedAt")
	var updatedAt: Date? = null


	constructor(nombre: String?, createdAt: Date?, updatedAt: Date?) : this() {
		this.nombre = nombre
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(id: Long?, nombre: String?, createdAt: Date?, updatedAt: Date?) : this() {
		this.id = id
		this.nombre = nombre
		this.createdAt = createdAt
		this.updatedAt = updatedAt
	}

	constructor(id: Long?, nombre: String?) : this() {
		this.id = id
		this.nombre = nombre
	}
}

class UsuarioPerfil(
	@Embedded
	var usuario: UsuarioEntity,

	@Relation(
		parentColumn = "id",
		entityColumn = "idUsuario",
		entity = PerfilEntity::class
	)
	var perfil: PerfilEntity? = null
)