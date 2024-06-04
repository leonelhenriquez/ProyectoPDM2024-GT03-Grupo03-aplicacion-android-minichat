package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import com.example.minichat.Commons.GenericEntity
import java.util.Date

@Entity(
  tableName = "mnt_chat",
  foreignKeys = [ForeignKey(
    entity = TipoChatEntity::class,
    parentColumns = ["id"],
    childColumns = ["idTipoChat"],
    onDelete = ForeignKey.CASCADE,
    onUpdate = ForeignKey.CASCADE
  )]
)
class ChatEntity() : GenericEntity() {
  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "id")
  var id: Long? = null

  @ColumnInfo(name = "uriFoto")
  var uriFoto: String? = null

  @ColumnInfo(name = "idTipoChat")
  var idTipoChat: Long? = null

  @ColumnInfo(name = "fechaCreacion")
  var fechaCreacion: Date? = null

  @ColumnInfo(name = "updatedAt")
  var updatedAt: Date? = null

  /*@Relation(
    parentColumn = "id",
    entityColumn = "id_chat",
    entity = MensajeEntity::class
  )
  var mensajes: List<MensajeEntity>? = null

  @Relation(
    parentColumn = "id",
    entityColumn = "idChat",
    entity = PreferenciaChatEntity::class
  )
  var preferenciasChat: List<PreferenciaChatEntity>? = null*/

  /*@Relation(
    parentColumn = "id",
    entityColumn = "idChat",
    entity = UsuarioChatEntity::class,
  )
  var usuariosChatList: List<UsuarioChatEntity> = listOf()*/


  constructor(
    uriFoto: String?,
    idTipoChat: Long?,
    fechaCreacion: Date?,
    updatedAt: Date?
  ) : this() {
    this.uriFoto = uriFoto
    this.idTipoChat = idTipoChat
    this.fechaCreacion = fechaCreacion
    this.updatedAt = updatedAt
  }

  constructor(
    id: Long?,
    uriFoto: String?,
    idTipoChat: Long?,
    fechaCreacion: Date?,
    updatedAt: Date?
  ) : this() {
    this.id = id
    this.uriFoto = uriFoto
    this.idTipoChat = idTipoChat
    this.fechaCreacion = fechaCreacion
    this.updatedAt = updatedAt
  }
}

data class ChatWithMensajes(
  @Embedded val chat: ChatEntity,
  @Relation(
    parentColumn = "id",
    entityColumn = "idChat"
  )
  var mensajes: List<MensajeEntity> = listOf(),
  // tipo chat
  @Relation(
    parentColumn = "idTipoChat",
    entityColumn = "id"
  )
  var tipoChat: TipoChatEntity? = null,
  // preferencias chat
  @Relation(
    parentColumn = "id",
    entityColumn = "idChat"
  )
  var preferenciasChat: List<PreferenciaChatEntity> = listOf(),
  // usuarios chat
  @Relation(
    parentColumn = "id",
    entityColumn = "idChat"
  )
  var usuariosChatList: List<UsuarioChatEntity> = listOf()
)

data class ChatWithData(
  @Embedded val chat: ChatEntity,
  // tipo chat
  @Relation(
    parentColumn = "idTipoChat",
    entityColumn = "id"
  )
  var tipoChat: TipoChatEntity? = null,
  // preferencias chat
  @Relation(
    parentColumn = "id",
    entityColumn = "idChat",
  )
  var preferenciasChat: List<PreferenciaChatEntity> = listOf(),
  // usuarios chat
  @Relation(
    parentColumn = "id",
    entityColumn = "id",
    entity = UsuarioEntity::class,
    associateBy = Junction(
      value = UsuarioChatEntity::class,
      parentColumn = "idChat",
      entityColumn = "idUsuario"
    )
  )
  var usuariosChatList: List<UsuarioPerfil> = listOf(),

  // ultimo mensaje
  @Relation(
    parentColumn = "id",
    entityColumn = "id_chat",
    entity = MensajeEntity::class
  )
  var mensaje: MensajeEntity? = null

)