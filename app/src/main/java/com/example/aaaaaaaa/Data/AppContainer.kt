package com.example.aaaaaaaa.Data

import android.content.Context

interface AppContainer {
    val peliculasRepository : PeliculaRepository
}

class AppDataContainer(private val context: Context) : AppContainer{
    override val peliculasRepository: PeliculaRepository by lazy{
        LocalPeliculaRepository(AppDatabase.getDatabase(context), peliculaDao())
    }
}