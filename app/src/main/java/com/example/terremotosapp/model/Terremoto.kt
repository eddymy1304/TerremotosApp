package com.example.terremotosapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "terremoto")
data class Terremoto(
    @PrimaryKey
    val id:String,
    val lugar:String,
    val magnitud:Double,
    val hora:Long,
    val longitud:Double,
    val latitud:Double
    )