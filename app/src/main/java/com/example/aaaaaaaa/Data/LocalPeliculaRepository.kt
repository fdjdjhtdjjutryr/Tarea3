package com.example.aaaaaaaa.Data

import kotlinx.coroutines.flow.Flow

class LocalPeliculaRepository(private val itemDao: PeliculaDao): PeliculaRepository {
    override fun getAllPeliculaStream(): Flow<List<Pelicula>> = itemDao.getAllItems()

    override fun getPeliculaStream(id: Int): Flow<Pelicula?> = itemDao.getItem(id)

    override suspend fun insertPelicula(pelicula: Pelicula)= itemDao.insert(pelicula)

    override suspend fun deletePelicula(pelicula: Pelicula) = itemDao.delete(pelicula)

    override suspend fun updatePelicula(pelicula: Pelicula) = itemDao.update(pelicula)
}