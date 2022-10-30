package com.example.terremotosapp

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET


interface TerremotoApiService {
  @GET("all_hour.geojson")
  //fun listRepos(/*@Path("user") user: String? CUANDO SE ENVIA DATA*/): Call<List<Repo?>?>? RESPUESTA QUE SE RECIBE DEL SERVIDOR
  fun obtenerTerremotosUltimaHora(): String
}

private var retrofit = Retrofit.Builder()
  .baseUrl("https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/")
  .addConverterFactory(ScalarsConverterFactory.create())
  .build()

var service: TerremotoApiService = retrofit.create(TerremotoApiService::class.java)