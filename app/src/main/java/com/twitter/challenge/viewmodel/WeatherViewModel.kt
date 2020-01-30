package com.twitter.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.twitter.challenge.model.Climate
import com.twitter.challenge.repository.WeatherRepository

class WeatherViewModel : ViewModel() {
    val weather : LiveData<Climate> = WeatherRepository.getCurrent()
    private val nextFiveDaysSD = MutableLiveData<Float>()

    fun updateNextFiveDaysSD(): LiveData<Float> {
        nextFiveDaysSD.value = 0.1f
        return nextFiveDaysSD
    }


    fun getFiveDaysSD() : LiveData<Float> = nextFiveDaysSD
}