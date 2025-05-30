package com.example.aaaaaaaa.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pelicula::class],version =1 , exportSchema = false)
abstract class  AppDatabase: RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao

    companion object{
        @Volatile
        private var Instance : AppDatabase? =null
        fun getDatabase(context:Context):AppDatabase{
            //
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context,AppDatabase::class.java, "App_database")
                    .build().also{ Instance=it}
            }

        }
    }
}