package com.engalshikh.eartgqyaje

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory


class EarthquakeFetchr {
    lateinit var  eathquakapi:EarthquakeApi

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://earthquake.usgs.gov")
            .build()
         eathquakapi = retrofit.create(EarthquakeApi::class.java)
    }


    fun feachData(): MutableLiveData<List<Earthquake>> {
        val responseLiveData: MutableLiveData<List<Earthquake>> = MutableLiveData()
        val eathquakHomePageRequest: Call<ErthResponse> = eathquakapi.fetchContents()
        eathquakHomePageRequest.enqueue(object : Callback<ErthResponse> {
            override fun onResponse(call: Call<ErthResponse>, response: Response<ErthResponse>) {


                var erthResponse=response.body()
               var eathquakes= erthResponse?.erthR
                   ?: mutableListOf()
                responseLiveData.value=eathquakes
                Log.d("test", response.body().toString())


            }

            override fun onFailure(call: Call<ErthResponse>, t: Throwable) {


            }

        })

        return responseLiveData

    }


}