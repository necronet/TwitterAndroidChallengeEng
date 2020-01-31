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
    fun getCurrent() : LiveData<Climate> {
        val data = MutableLiveData<Climate>()
        NetworkModule.provideWeatherService().getCurrent().enqueue(object: Callback<Climate> {

            override fun onResponse(call: Call<Climate>, response: Response<Climate>) {
                Log.i("DEBUG", "body ${response.body()}");
                data.value = response.body()
            }

            override fun onFailure(call: Call<Climate>, t: Throwable) {
                TODO("UPPSS ERROR ${t.message}")
            }
        })

        return data
    }

    suspend fun getFuture(day: String) : Climate = NetworkModule.provideWeatherService().getFuture(day)
}