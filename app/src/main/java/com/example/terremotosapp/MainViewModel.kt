package com.example.terremotosapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private val trabajo = Job()
    private val corutina = CoroutineScope(Dispatchers.Main + trabajo)

    private val _terremotoList = MutableLiveData<MutableList<Terremoto>>()
    val terremotoList: LiveData<MutableList<Terremoto>>
            get() = _terremotoList

    init {
        corutina.launch {
            _terremotoList.value = buscarTerremoto()
        }
    }

    private suspend fun buscarTerremoto(): MutableList<Terremoto> {
        return withContext(Dispatchers.IO){
            val misTerremotos = DatosTerremoto().cargarTerremotos()
            misTerremotos
        }
    }

    override fun onCleared() {
        super.onCleared()
        trabajo.cancel()
    }


}