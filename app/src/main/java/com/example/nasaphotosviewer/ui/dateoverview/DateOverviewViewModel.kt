package com.example.nasaphotosviewer.ui.dateoverview

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.network.NasaService
import kotlinx.coroutines.*

class DateOverviewViewModel(private val application: App) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    var dates: MutableLiveData<List<Date>> = MutableLiveData()

    init {
        getDatesList()
    }

    private fun getDatesList() {
      viewModelScope.launch {
          dates.postValue(
              application.nasaService.getDates()
          )
        }
    }
}