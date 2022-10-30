package com.example.terremotosapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class MainViewModel: ViewModel() {

    private val _terremotoList = MutableLiveData<MutableList<Terremoto>>()
    val terremotoList: LiveData<MutableList<Terremoto>>
            get() = _terremotoList

    init {
        viewModelScope.launch {
            _terremotoList.value = buscarTerremoto()
        }
    }

    private suspend fun buscarTerremoto(): MutableList<Terremoto> {
        return withContext(Dispatchers.IO){
            val misTerremotos = DatosTerremoto().cargarTerremotos()
            misTerremotos
        }
    }

}