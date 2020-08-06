package com.example.nasaphotosviewer.data.network

import com.example.nasaphotosviewer.data.model.Date
import com.example.nasaphotosviewer.data.model.Photo
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

private const val BASE_URL = "https://api.nasa.gov/EPIC/api/"

class NasaService : NetworkService {
    val api = createRetrofit().create(NasaApi::class.java)

    companion object {
        @JvmField
        var KEY = "bUPDj3NcY7TPvoShGVEilLJJmiYHzdqyirJx04n4"
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(createOkHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val original = chain.request()
                val originalHttpUrl = original.url
                val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", KEY)
                    .build()
                val requestBuilder = original.newBuilder()
                    .url(url)
                val request = requestBuilder.build()
                return chain.proceed(request)
            }
        })
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        return httpClient.build()
    }

    override fun getDates(): List<Date>? {
        val response = api.getDatesWithPhoto().execute()
        return response.body()
    }

    override fun getPhotosForDate(date: String): List<Photo>? {
        val response = api.getPhotosForDate(date).execute()
        return response.body()
    }
}