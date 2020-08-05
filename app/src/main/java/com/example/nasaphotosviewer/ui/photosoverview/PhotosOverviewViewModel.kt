package com.example.nasaphotosviewer.ui.photosoverview

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.data.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job

class PhotosOverviewViewModel(private val application: App) : AndroidViewModel(application) {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private var _photoClicked = MutableLiveData<Boolean>()
    val photoClicked: LiveData<Boolean>
        get() = _photoClicked

    fun onPhotoClick() {
        _photoClicked.value = true
    }


}