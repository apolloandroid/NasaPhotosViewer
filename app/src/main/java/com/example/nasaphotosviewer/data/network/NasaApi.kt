package com.example.nasaphotosviewer.data.network

import com.example.nasaphotosviewer.data.model.DateResponse
import com.example.nasaphotosviewer.data.model.PhotoResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface NasaApi {

    @GET("natural/all")
    fun getDatesWithPhoto(): Single<List<DateResponse>?>?

    @GET("natural/date/{date}")
    fun getPhotosForDate(@Path("date") date: String?): Single<List<PhotoResponse>?>?
}