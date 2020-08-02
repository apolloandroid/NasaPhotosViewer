package com.example.nasaphotosviewer.ui.dateoverview

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class DateOverviewViewModelFactory(private val application: Application) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DateOverviewViewModel::class.java)) {
            return DateOverviewViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}