package com.alice.teampang.services

import com.alice.teampang.src.main.`when`.model.WhenResponse
import retrofit2.Call
import retrofit2.http.GET

interface Services {
    @GET("/meetings")
    fun getWhen(): Call<WhenResponse>
}