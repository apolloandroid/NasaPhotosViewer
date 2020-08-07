package com.example.nasaphotosviewer.ui.photodetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaphotosviewer.App

class PhotoDetailsViewModelFactory(private val application: App) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotoDetailsViewModel::class.java)) {
            return PhotoDetailsViewModel(application) as T
        }
        throw   IllegalArgumentException("Unknown ViewModel class")
    }
}