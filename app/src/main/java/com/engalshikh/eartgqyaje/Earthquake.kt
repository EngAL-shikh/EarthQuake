package com.engalshikh.eartgqyaje

import com.google.gson.annotations.SerializedName

data class Earthquake (
    @SerializedName("properties")
    var pro:Pro=Pro(),

    @SerializedName("geometry")
    var geo:Geo=Geo()
)