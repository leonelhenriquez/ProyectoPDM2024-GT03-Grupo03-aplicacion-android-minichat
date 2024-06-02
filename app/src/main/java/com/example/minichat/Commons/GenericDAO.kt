package com.example.minichat.Commons

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Update
import androidx.room.Upsert

interface GenericDAO<T> {
	@Insert
	fun insert(t: T)

	@Delete
	fun delete(t: T)

	@Update
	fun update(t: T)

	@Upsert
	fun upsert(t: T)
}
