package com.example.nasaphotosviewer.data

import android.app.Application
import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo
import com.example.nasaphotosviewer.data.network.NasaService
import com.example.nasaphotosviewer.data.network.NetworkService

class NasaRepository : Repository {
    companion object {
        fun getInstance(application: Application): Repository {
            var instance: Repository? = null
            if (instance == null) {
                instance = NasaRepository()
            }
            return instance
        }
    }

    private val networkService: NetworkService =
        NasaService()

    override suspend fun getDates(): List<Date>? {
        return networkService.getDates()
    }

    override suspend fun getPhotosForDate(photo: String): List<Photo>? {
        return networkService.getPhotosForDate(photo)
    }
}