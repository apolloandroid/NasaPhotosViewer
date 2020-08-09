package com.example.nasaphotosviewer.data.network

import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo

interface NetworkService {
    fun getDates(): List<Date>?
    fun getPhotosForDate(date: String): List<Photo>?
}