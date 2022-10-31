package com.example.terremotosapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.terremotosapp.model.MainRepository
import com.example.terremotosapp.model.Terremoto
import kotlinx.coroutines.*

private val TAG = MainViewModel::class.java.simpleName

class MainViewModel : ViewModel() {

    private val _terremotoList = MutableLiveData<MutableList<Terremoto>>()
    val terremotoList: LiveData<MutableList<Terremoto>>
        get() = _terremotoList

    private val repository = MainRepository()

    init {
        viewModelScope.launch {
            _terremotoList.value = repository.buscarTerremoto()
        }
    }



}