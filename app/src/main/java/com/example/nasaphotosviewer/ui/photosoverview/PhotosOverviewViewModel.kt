package com.example.nasaphotosviewer.ui.photosoverview

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.nasaphotosviewer.App
import com.example.nasaphotosviewer.data.model.Photo
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PhotosOverviewViewModel(private val application: App, private val date: String) :
    AndroidViewModel(application), PhotosListAdapter.OnPhotoClickListener<Photo> {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private var _photoClicked = MutableLiveData<Boolean>()
    val photoClicked: LiveData<Boolean>
        get() = _photoClicked

    fun getPhotosForDate(date: String) {
        viewModelScope.launch {
            _photos.postValue(
                application.repository.getPhotosForDate(date)
            )
        }
    }

    override fun onPhotoClick() {
        _photoClicked.value = true
    }
}