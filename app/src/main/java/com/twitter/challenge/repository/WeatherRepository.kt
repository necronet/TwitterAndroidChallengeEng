package com.twitter.challenge.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.twitter.challenge.remote.NetworkModule
import com.twitter.challenge.model.Climate
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object WeatherRepository  {
    suspend fun getCurrent() = NetworkModule.provideWeatherService().getCurrent()

    suspend fun getFuture(day: String) : Climate = NetworkModule.provideWeatherService().getFuture(day)
}