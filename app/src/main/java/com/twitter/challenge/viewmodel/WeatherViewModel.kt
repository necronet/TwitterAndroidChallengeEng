package com.twitter.challenge.viewmodel

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.twitter.challenge.core.Statistic
import com.twitter.challenge.model.Climate
import com.twitter.challenge.remote.WeatherService
import com.twitter.challenge.repository.WeatherRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {
    private val isBusy = MutableLiveData<Boolean>(false)
    private val standardDeviation = MutableLiveData<Float>()
    private val weather = MutableLiveData<Climate>()

    fun isBusy() : LiveData<Boolean> = isBusy
    fun getStandardDeviation() : LiveData<Float> = standardDeviation
    fun getWeather() : LiveData<Climate> {
        CoroutineScope(Dispatchers.IO).launch {
            weather.postValue(WeatherRepository.getCurrent())
        }
        return weather
    }

    fun updateNextFiveDaysSD() = CoroutineScope(Dispatchers.IO).launch {
        if (isBusy.value!!) return@launch
        isBusy.postValue(true)
        val temperatureNextFiveDays = (1..5).map { day -> WeatherRepository.getFuture(day.toString()).weather.temp }
        isBusy.postValue(false)
        standardDeviation.postValue(Statistic.standardDeviation(temperatureNextFiveDays))
    }
}