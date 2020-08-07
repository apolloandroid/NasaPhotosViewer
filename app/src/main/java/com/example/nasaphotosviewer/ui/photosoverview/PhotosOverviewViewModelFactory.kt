package com.example.nasaphotosviewer.ui.photosoverview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.nasaphotosviewer.App
import java.lang.IllegalArgumentException

class PhotosOverviewViewModelFactory(private val application: App) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PhotosOverviewViewModel::class.java)) {
            return PhotosOverviewViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}