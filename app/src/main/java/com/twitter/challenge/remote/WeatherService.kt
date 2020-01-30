package com.twitter.challenge.remote

import com.twitter.challenge.model.Climate
import retrofit2.Call
import retrofit2.http.GET

interface WeatherService {

    @GET("/current.json")
    fun getCurrent(): Call<Climate>
}