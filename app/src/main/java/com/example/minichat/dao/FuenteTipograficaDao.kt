package com.example.minichat.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.minichat.Commons.GenericDAO
import com.example.minichat.entities.FuenteTipograficaEntity

@Dao
interface FuenteTipograficaDao : GenericDAO<FuenteTipograficaEntity>
{
	@Query("SELECT * FROM ctl_fuente_tipografica WHERE id = :id")
	fun findById(id: Int): FuenteTipograficaEntity

	@Query("SELECT * FROM ctl_fuente_tipografica")
	fun findAll(): List<FuenteTipograficaEntity>
}