package com.example.aaaaaaaa.Data

import kotlinx.coroutines.flow.Flow

interface PeliculaRepository {
    fun getAllPeliculaStream(): Flow<List<Pelicula>>

    fun getPeliculaStream(id: Int): Flow<Pelicula?>

    suspend fun insertPelicula(pelicula: Pelicula)

    suspend fun deletePelicula(pelicula: Pelicula)

    suspend fun updatePelicula(pelicula: Pelicula)
}