package com.example.aaaaaaaa.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Pelicula::class],version =1 , exportSchema = false)
abstract class DataBase: RoomDatabase() {

    abstract fun peliculaDao(): PeliculaDao

    companion object{
        @Volatile
        private var Instance : DataBase? =null
        fun getDatabase(context:Context):DataBase{
            //
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context,DataBase::class.java, "App_database")
                    .build().also{ Instance=it}
            }

        }
    }
}