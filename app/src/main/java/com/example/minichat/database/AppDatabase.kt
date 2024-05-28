package com.example.minichat.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.minichat.Commons.DateConverter
import com.example.minichat.dao.ArchivoMensajeDao
import com.example.minichat.dao.ChatDao
import com.example.minichat.dao.CodigoGeneradoUsuarioDao
import com.example.minichat.dao.ContactoBloqueadoDao
import com.example.minichat.dao.ContactoUsuarioDao
import com.example.minichat.dao.DispositivoVinculadoDao
import com.example.minichat.dao.DobleFactorUsuarioDao
import com.example.minichat.dao.FuenteTipograficaDao
import com.example.minichat.dao.LecturaMensajeDao
import com.example.minichat.dao.MensajeDao
import com.example.minichat.dao.OpcionMenuDao
import com.example.minichat.dao.PerfilDao
import com.example.minichat.dao.PreferenciaChatDao
import com.example.minichat.dao.PreferenciaNotificacionDao
import com.example.minichat.dao.PreferenciasUsuarioDao
import com.example.minichat.dao.ReaccionDao
import com.example.minichat.dao.ReaccionMensajeDao
import com.example.minichat.dao.RolDao
import com.example.minichat.dao.RolOpcionMenuDao
import com.example.minichat.dao.TipoArchivoDao
import com.example.minichat.dao.TipoChatDao
import com.example.minichat.dao.UsuarioChatDao
import com.example.minichat.dao.UsuarioDao
import com.example.minichat.entities.ArchivoMensajeEntity
import com.example.minichat.entities.ChatEntity
import com.example.minichat.entities.CodigoGeneradoUsuarioEntity
import com.example.minichat.entities.ContactoBloqueadoEntity
import com.example.minichat.entities.ContactoUsuarioEntity
import com.example.minichat.entities.DispositivoVinculadoEntity
import com.example.minichat.entities.DobleFactorUsuarioEntity
import com.example.minichat.entities.FuenteTipograficaEntity
import com.example.minichat.entities.LecturaMensajeEntity
import com.example.minichat.entities.MensajeEntity
import com.example.minichat.entities.OpcionMenuEntity
import com.example.minichat.entities.PerfilEntity
import com.example.minichat.entities.PreferenciaChatEntity
import com.example.minichat.entities.PreferenciaNotificacionEntity
import com.example.minichat.entities.PreferenciasUsuarioEntity
import com.example.minichat.entities.ReaccionEntity
import com.example.minichat.entities.ReaccionMensajeEntity
import com.example.minichat.entities.RolEntity
import com.example.minichat.entities.RolOpcionMenuEntity
import com.example.minichat.entities.TipoArchivoEntity
import com.example.minichat.entities.TipoChatEntity
import com.example.minichat.entities.UsuarioChatEntity
import com.example.minichat.entities.UsuarioEntity

@Database(entities = [
  FuenteTipograficaEntity::class,
  OpcionMenuEntity::class,
  ReaccionEntity::class,
  RolEntity::class,
  TipoArchivoEntity::class,
  TipoChatEntity::class,
  UsuarioEntity::class,
  ChatEntity::class,
  PreferenciaNotificacionEntity::class,
  PreferenciasUsuarioEntity::class,
  DispositivoVinculadoEntity::class,
  PerfilEntity::class,
  DobleFactorUsuarioEntity::class,
  CodigoGeneradoUsuarioEntity::class,
  RolOpcionMenuEntity::class,
  MensajeEntity::class,
  LecturaMensajeEntity::class,
  ArchivoMensajeEntity::class,
  ReaccionMensajeEntity::class,
  PreferenciaChatEntity::class,
  UsuarioChatEntity::class,
  ContactoUsuarioEntity::class,
  ContactoBloqueadoEntity::class,
], version = 1)

@TypeConverters(value = [DateConverter::class])
abstract class AppDatabase : RoomDatabase() {
	companion object {
		@Volatile
		private var INSTANCE: AppDatabase? = null

		fun getDatabase(context: Context): AppDatabase {
			return INSTANCE ?: synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					AppDatabase::class.java,
					"db_minichat"
				)
					.allowMainThreadQueries()
					.addCallback(DB_CALLBACK)
					.build()
				INSTANCE = instance
				instance
			}
		}

		private val DB_CALLBACK: Callback = object : Callback() {
			override fun onCreate(db: SupportSQLiteDatabase) {
				super.onCreate(db)
			}
		}
	}

	abstract fun fuenteTipograficaDao(): FuenteTipograficaDao
	abstract fun opcionMenuDao(): OpcionMenuDao
	abstract fun reaccionDao(): ReaccionDao
	abstract fun rolDao(): RolDao
	abstract fun tipoArchivoDao(): TipoArchivoDao
	abstract fun tipoChatDao(): TipoChatDao
	abstract fun usuarioDao(): UsuarioDao
	abstract fun chatDao(): ChatDao
	abstract fun preferenciaNotificacionDao(): PreferenciaNotificacionDao
	abstract fun preferenciasUsuarioDao(): PreferenciasUsuarioDao
	abstract fun dispositivoVinculadoDao(): DispositivoVinculadoDao
	abstract fun perfilDao(): PerfilDao
	abstract fun dobleFactorUsuarioDao(): DobleFactorUsuarioDao
	abstract fun codigoGeneradoUsuarioDao(): CodigoGeneradoUsuarioDao
	abstract fun rolOpcionMenuDao(): RolOpcionMenuDao
	abstract fun mensajeDao(): MensajeDao
	abstract fun lecturaMensajeDao(): LecturaMensajeDao
	abstract fun archivoMensajeDao(): ArchivoMensajeDao
	abstract fun reaccionMensajeDao(): ReaccionMensajeDao
	abstract fun preferenciaChatDao(): PreferenciaChatDao
	abstract fun usuarioChatDao(): UsuarioChatDao
	abstract fun contactoUsuarioDao(): ContactoUsuarioDao
	abstract fun contactoBloqueadoDao(): ContactoBloqueadoDao


}
