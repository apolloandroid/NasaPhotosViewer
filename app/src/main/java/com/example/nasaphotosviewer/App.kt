package com.example.nasaphotosviewer

import android.app.Application
import com.example.nasaphotosviewer.data.NasaRepository
import com.example.nasaphotosviewer.data.Repository

object App : Application() {
    val repository: Repository = NasaRepository.getInstance(this)
}