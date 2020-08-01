package com.example.nasaphotosviewer.data.model

import com.example.nasaphotosviewer.data.network.NasaService


data class PhotoResponse(
    var identifier: String? = null,
    var caption: String? = null,
    var image: String? = null,
    var date: String? = null
) {
    //https://api.nasa.gov/EPIC/archive/enhanced/2016/12/04/png/epic_RBG_20161204003633.png?api_key=DEMO_KEY
    val imageUrl: String
        get() {
//            https://api.nasa.gov/EPIC/archive/enhanced/2016/12/04/png/epic_RBG_20161204003633.png?api_key=DEMO_KEY
            val sb = StringBuilder()
            sb.append("https://api.nasa.gov/EPIC/archive/natural/")
            val dateComponents =
                date!!.split(" ".toRegex()).toTypedArray()[0].split("-".toRegex()).toTypedArray()
            sb
                .append(dateComponents[0]).append('/')
                .append(dateComponents[1]).append('/')
                .append(dateComponents[2]).append("/png/")
                .append(image).append(".png?api_key=").append(NasaService.KEY)
            return sb.toString()
        }
}