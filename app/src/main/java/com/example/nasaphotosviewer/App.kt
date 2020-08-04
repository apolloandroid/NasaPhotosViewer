package com.example.nasaphotosviewer

import android.app.Application
import com.example.nasaphotosviewer.data.NasaRepository
import com.example.nasaphotosviewer.data.Repository
import com.example.nasaphotosviewer.data.network.NasaService
import com.example.nasaphotosviewer.data.network.NetworkService
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache
import com.nostra13.universalimageloader.core.DisplayImageOptions
import com.nostra13.universalimageloader.core.ImageLoader
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration

class App : Application() {
    val repository: Repository = NasaRepository()

    override fun onCreate() {
        super.onCreate()

        val displayImageOptions = DisplayImageOptions.Builder()
            .cacheInMemory(true)
            .build()
        val config = ImageLoaderConfiguration.Builder(this)
            .defaultDisplayImageOptions(displayImageOptions)
            .memoryCache(LruMemoryCache(20 * 1024 * 1024))
            .memoryCacheSize(20 * 1024 * 1024)
            .build()

        ImageLoader.getInstance().init(config)
    }
}