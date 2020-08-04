package com.example.nasaphotosviewer.data

import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo

interface Repository {
    suspend fun getDates(): List<Date>?
    suspend fun getPhotosForDate(): List<Photo>?
}