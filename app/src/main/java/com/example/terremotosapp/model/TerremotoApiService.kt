package com.example.terremotosapp.model

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.converter.moshi.MoshiConverterFactory


interface TerremotoApiService {
    @GET("all_hour.geojson")
    //fun listRepos(/*@Path("user") user: String? CUANDO SE ENVIA DATA*/): Call<List<Repo?>?>? RESPUESTA QUE SE RECIBE DEL SERVIDOR
    suspend fun obtenerTerremotosUltimaHora(): TerremotoJsonResponse
}

private var retrofit = Retrofit.Builder()
    .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
    .addConverterFactory(MoshiConverterFactory.create())
    .build()

var service: TerremotoApiService = retrofit.create(TerremotoApiService::class.java)