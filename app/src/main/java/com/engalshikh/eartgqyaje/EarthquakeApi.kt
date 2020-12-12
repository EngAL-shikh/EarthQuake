package com.engalshikh.eartgqyaje

import retrofit2.Call
import retrofit2.http.GET

interface EarthquakeApi {

    @GET("/fdsnws/event/1//query?format=geojson&limit=10")
    fun fetchContents(): Call<ErthResponse>

}