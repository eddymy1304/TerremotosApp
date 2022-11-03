package com.example.terremotosapp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.terremotosapp.R
import com.example.terremotosapp.model.ApiResponseStatus
import com.example.terremotosapp.model.MainRepository
import com.example.terremotosapp.model.Terremoto
import com.example.terremotosapp.model.getTerremotoDatabase
import kotlinx.coroutines.*
import java.net.UnknownHostException

private val TAG = MainViewModel::class.java.simpleName

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _status = MutableLiveData<ApiResponseStatus>()
    val status: LiveData<ApiResponseStatus>
        get() = _status

    private val database = getTerremotoDatabase(application.applicationContext)
    private val repository = MainRepository(database)

    val terremotoList = repository.terremotoList

    init {
        viewModelScope.launch {
            // TRY Catch cuando no hay internet
            try {
                _status.value = ApiResponseStatus.LOADING
                repository.buscarTerremoto()
                _status.value = ApiResponseStatus.DONE
            } catch (e: UnknownHostException) {
                _status.value = ApiResponseStatus.ERROR
                Log.e(TAG, application.applicationContext.getString(R.string.no_internet), e)
            }

        }
    }


}