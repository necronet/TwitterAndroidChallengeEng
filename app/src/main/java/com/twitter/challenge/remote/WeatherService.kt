package com.twitter.challenge.remote

import com.twitter.challenge.model.Climate
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("/current.json")
    suspend fun getCurrent(): Climate

    @GET("/future_{day}.json")
    suspend fun getFuture(@Path("day") day: String): Climate
}