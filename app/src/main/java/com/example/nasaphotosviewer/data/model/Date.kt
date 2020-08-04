package com.example.nasaphotosviewer.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Date(
    @SerializedName("date")
    @Expose
    val date: String)