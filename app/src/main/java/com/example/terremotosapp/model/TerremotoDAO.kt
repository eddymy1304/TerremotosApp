package com.example.terremotosapp.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TerremotoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(terremotoList: MutableList<Terremoto>)

    @Query("SELECT * FROM terremoto")
    fun getTerremotos(): LiveData<MutableList<Terremoto>>

    @Query("SELECT * FROM terremoto ORDER BY magnitud ASC")
    fun getTerremotosporMagnitud(): LiveData<MutableList<Terremoto>>
}