package com.engalshikh.eartgqyaje

import com.google.gson.annotations.SerializedName

data class Pro(
    @SerializedName("mag")
    var mag: Double =0.0,
    @SerializedName("title")
    var title:String="",
    @SerializedName("time")
    var time:Long=3,
    @SerializedName("place")
    var place:String="",

){
}