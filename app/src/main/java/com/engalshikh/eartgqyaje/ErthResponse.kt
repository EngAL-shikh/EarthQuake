package com.engalshikh.eartgqyaje

import com.google.gson.annotations.SerializedName

data class ErthResponse (
    @SerializedName("features")
    var erthR:List<Earthquake>
)
