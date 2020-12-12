package com.engalshikh.eartgqyaje

import com.google.gson.annotations.SerializedName

data class Geo (
    @SerializedName("coordinates")
    var  geos:List<Double> = emptyList()
)


