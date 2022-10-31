package com.example.terremotosapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import org.json.JSONObject

private val TAG = MainViewModel::class.java.simpleName

class MainViewModel : ViewModel() {

    private val _terremotoList = MutableLiveData<MutableList<Terremoto>>()
    val terremotoList: LiveData<MutableList<Terremoto>>
        get() = _terremotoList

    init {
        viewModelScope.launch {
            _terremotoList.value = buscarTerremoto()
        }
    }

    private suspend fun buscarTerremoto(): MutableList<Terremoto> {
        return withContext(Dispatchers.IO) {
            val terremotoLisString = service.obtenerTerremotosUltimaHora()
            Log.e(TAG, terremotoLisString)
            val terremotoList = parsearLista(terremotoLisString)
            terremotoList
        }
    }

    private fun parsearLista(terremotoLisString: String): MutableList<Terremoto> {
        val terremotoList = mutableListOf<Terremoto>()
        val terremotoJsonObject = JSONObject(terremotoLisString)
        val feauturesJsonArray = terremotoJsonObject.getJSONArray("features")

        for (i in 0 until feauturesJsonArray.length()) {
            val featuresJSONObject = feauturesJsonArray[i] as JSONObject
            val id = featuresJSONObject.getString("id")

            val propertiesJSONObject = featuresJSONObject.getJSONObject("properties")
            val magnitud = propertiesJSONObject.getDouble("mag")
            val lugar = propertiesJSONObject.getString("place")
            val hora = propertiesJSONObject.getLong("time")

            val geometryJSONObject = featuresJSONObject.getJSONObject("geometry")
            val coordinatesJSONArray = geometryJSONObject.getJSONArray("coordinates")
            val longitud = coordinatesJSONArray.getDouble(0)
            val latitud = coordinatesJSONArray.getDouble(1)

            val terremoto = Terremoto(id, lugar, magnitud, hora, longitud, latitud)
            terremotoList.add(terremoto)
        }

        return terremotoList
    }

}