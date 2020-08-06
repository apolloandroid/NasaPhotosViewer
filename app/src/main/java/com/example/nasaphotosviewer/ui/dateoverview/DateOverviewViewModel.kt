package com.example.nasaphotosviewer.ui.dateoverview

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.data.model.Date
import kotlinx.coroutines.*

class DateOverviewViewModel(private val application: App) : AndroidViewModel(application),
    DateListAdapter.OnDateClickListener<Date> {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _dates: MutableLiveData<List<Date>> = MutableLiveData()
    val dates: LiveData<List<Date>>
        get() = _dates

    private var _dateClicked = MutableLiveData<String>()
    val dateClicked: LiveData<String>
        get() = _dateClicked

    init {
        getDatesList()
    }

    private fun getDatesList() {
        viewModelScope.launch {
            _dates.postValue(
                application.repository.getDates()
            )
        }
    }

    override fun onDateClick(date:String) {
        _dateClicked.value = date
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}