package com.example.terremotosapp.model

import android.util.Log
import androidx.lifecycle.LiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

private val TAG = MainRepository::class.java.simpleName

class MainRepository(private val database: TerremotoDatabase) {

    suspend fun buscarTerremoto(ordenarPorMagnitud: Boolean): MutableList<Terremoto> {
        return withContext(Dispatchers.IO) {
            val terremotoJsonResponse = service.obtenerTerremotosUltimaHora()
            Log.e(TAG, terremotoJsonResponse.toString())
            val terremotoParseado = parsearLista(terremotoJsonResponse)
            Log.e(TAG, terremotoParseado.toString())
            //insertamos a la BD
            database.terremotoDAO.insertAll(terremotoParseado)
            buscarTerremotoDesdeBD(ordenarPorMagnitud)
        }
    }

    suspend fun buscarTerremotoDesdeBD(ordenarPorMagnitud: Boolean): MutableList<Terremoto> {
        return withContext(Dispatchers.IO) {
            when (ordenarPorMagnitud) {
                true -> database.terremotoDAO.getTerremotosporMagnitud()
                false -> database.terremotoDAO.getTerremotos()
            }
        }
    }

    private fun parsearLista(terremotoJsonResponse: TerremotoJsonResponse): MutableList<Terremoto> {
        val terremotoParseado = mutableListOf<Terremoto>()

        val features = terremotoJsonResponse.features

        for (feature in features) {
            val properties = feature.properties
            val geometry = feature.geometry

            val id = feature.id
            val magnitud = properties.mag
            val lugar = properties.place
            val hora = properties.time
            val longitud = geometry.longitude
            val latitud = geometry.latitude
            terremotoParseado.add(Terremoto(id, lugar, magnitud, hora, longitud, latitud))
        }
        return terremotoParseado
    }
}