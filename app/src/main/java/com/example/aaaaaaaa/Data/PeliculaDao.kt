package com.example.aaaaaaaa.Data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import androidx.room.Update

@Dao
interface PeliculaDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(pelicula:Pelicula)

    @Update
    suspend fun update(pelicula:Pelicula)

    @Delete
    suspend fun delete(pelicula: Pelicula)

    @Query("SELECT * from peliculas WHERE id = :id")
    fun getItem(id:Int): Flow<Pelicula>

    @Query("SELECT * from peliculas ORDER BY nombre ASC")
    fun getAllItems(): Flow<List<Pelicula>>
}