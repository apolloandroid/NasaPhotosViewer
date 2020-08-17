package com.example.nasaphotosviewer

import android.app.Application
import com.example.nasaphotosviewer.data.NasaRepository
import com.example.nasaphotosviewer.data.Repository

class App : Application() {
    val repository: Repository = NasaRepository.getInstance(this)
}