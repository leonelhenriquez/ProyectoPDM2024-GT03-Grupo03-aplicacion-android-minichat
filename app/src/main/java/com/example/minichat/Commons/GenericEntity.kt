package com.example.minichat.Commons

import java.io.Serializable

abstract class GenericEntity : Serializable{
	companion object {
		const val TABLE_NAME = "word_table"
	}
}