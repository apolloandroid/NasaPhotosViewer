package com.example.nasaphotosviewer.ui.dateoverview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.data.model.DateResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class DateOverviewViewModel(application: Application) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + Job())

    private val _datesList: MutableLiveData<List<DateResponse>> = getDatesList()
    val datesList: LiveData<List<DateResponse>>
        get() = _datesList

    private fun getDatesList(): MutableLiveData<List<DateResponse>> {
        return MutableLiveData()
    }

}