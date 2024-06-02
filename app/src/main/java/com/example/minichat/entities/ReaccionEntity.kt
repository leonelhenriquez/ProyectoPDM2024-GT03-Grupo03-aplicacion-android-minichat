package com.example.minichat.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.minichat.Commons.GenericEntity

@Entity(tableName = "ctl_reaccion")
class ReaccionEntity : GenericEntity() {
	@PrimaryKey(autoGenerate = true)
	@ColumnInfo(name = "id")
	var id: Long? = null

	@ColumnInfo(name = "valor")
	var valor: String? = null
}