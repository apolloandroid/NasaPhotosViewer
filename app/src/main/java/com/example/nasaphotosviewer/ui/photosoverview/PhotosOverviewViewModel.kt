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

class PhotosOverviewViewModel(private val application: App) :
    AndroidViewModel(application), PhotosListAdapter.OnPhotoClickListener<Photo> {
    private val viewModelJob = Job()
    private val viewModelScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    private val _photos: MutableLiveData<List<Photo>> = MutableLiveData()
    val photos: LiveData<List<Photo>>
        get() = _photos

    private var _photoClicked = MutableLiveData<String>()
    val photoClicked: LiveData<String>
        get() = _photoClicked

    fun getPhotosForDate(date: String) {
        viewModelScope.launch {
            _photos.postValue(
                application.repository.getPhotosForDate(date)
            )
        }
    }

    override fun onPhotoClick(photoUrl:String) {
        _photoClicked.value = photoUrl
    }
}