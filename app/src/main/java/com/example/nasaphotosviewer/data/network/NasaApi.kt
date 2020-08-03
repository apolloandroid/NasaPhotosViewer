package com.example.nasaphotosviewer.data.network

import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface NasaApi {

    @GET("natural/all")
    fun getDatesWithPhoto(): Call<List<Date>>

    @GET("natural/date/{date}")
    fun getPhotosForDate(@Path("date") date: String?): Call<List<Photo>>
}