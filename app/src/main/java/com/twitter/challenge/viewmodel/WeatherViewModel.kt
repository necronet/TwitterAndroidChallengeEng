package com.twitter.challenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.twitter.challenge.model.Climate
import com.twitter.challenge.repository.WeatherRepository
import javax.inject.Inject

class WeatherViewModel : ViewModel() {
    val weather : LiveData<Climate> = WeatherRepository.getCurrent()
}