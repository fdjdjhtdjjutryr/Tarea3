package com.example.aaaaaaaa.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "peliculas")
data class Pelicula(
    @PrimaryKey
    val id: Int = 0 ,
    val nombre: String,
    val anio: String,
    val descripcion: String,
    val ratingIMDB: String
)
