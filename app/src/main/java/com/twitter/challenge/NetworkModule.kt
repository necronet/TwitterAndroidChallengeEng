package com.twitter.challenge

import com.twitter.challenge.remote.WeatherService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://twitter-code-challenge.s3.amazonaws.com"

object NetworkModule {

    fun provideWeatherService() = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(WeatherService::class.java)

}