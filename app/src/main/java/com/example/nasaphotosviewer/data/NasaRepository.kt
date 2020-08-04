package com.example.nasaphotosviewer.data

import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo
import com.example.nasaphotosviewer.data.network.NasaService
import com.example.nasaphotosviewer.data.network.NetworkService

class NasaRepository : Repository {
    private val networkService: NetworkService =
        NasaService()

    override suspend fun getDates(): List<Date>? {
        return networkService.getDates()
    }

    override suspend fun getPhotosForDate(): List<Photo>? {
        return networkService.getPhotosForDate()
    }
}