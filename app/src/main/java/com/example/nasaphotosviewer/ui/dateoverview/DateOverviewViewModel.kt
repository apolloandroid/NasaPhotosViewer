package com.example.nasaphotosviewer.ui.dateoverview

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.data.model.Date
import kotlinx.coroutines.*

class DateOverviewViewModel(private val application: App) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _dates: MutableLiveData<List<Date>> = MutableLiveData()
    val dates: LiveData<List<Date>>
        get() = _dates

    private var _dateClicked = MutableLiveData<Boolean>()
    val dateClicked: LiveData<Boolean>
        get() = _dateClicked

    init {
        getDatesList()
    }

    fun onDateClick() {
        _dateClicked.value = false
    }

    private fun getDatesList() {
        viewModelScope.launch {
            _dates.postValue(
                application.repository.getDates()
            )
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}