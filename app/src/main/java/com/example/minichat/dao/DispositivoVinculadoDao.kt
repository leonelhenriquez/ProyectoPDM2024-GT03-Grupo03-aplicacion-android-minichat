package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.DispositivoVinculadoEntity

@Dao
interface DispositivoVinculadoDao : GenericDAO<DispositivoVinculadoEntity> {
    @Query("SELECT * FROM mnt_dispositivo_vinculado WHERE idUsuario = :idUsuario")
    fun getDispositivoVinculado(idUsuario: Long): DispositivoVinculadoEntity?
}