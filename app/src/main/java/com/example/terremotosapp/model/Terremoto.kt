package com.example.terremotosapp.model

data class Terremoto(
    val id:String,
    val lugar:String,
    val magnitud:Double,
    val hora:Long,
    val longitud:Double,
    val latitud:Double
    )