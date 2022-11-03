package com.example.terremotosapp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Terremoto::class], version = 1)
abstract class TerremotoDatabase : RoomDatabase() {
    abstract val terremotoDAO: TerremotoDAO
}

private lateinit var INSTANCE: TerremotoDatabase

fun getTerremotoDatabase(context: Context): TerremotoDatabase {
    synchronized(TerremotoDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                TerremotoDatabase::class.java,
                "terremoto_db"
            ).build()
        }
        return INSTANCE
    }
}