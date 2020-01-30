package com.twitter.challenge.model

data class Climate(val name:String, val coord: Coord, val weather: Weather, val wind: Wind, val cloud: Cloud)
data class Coord(val lon: Float, val lat: Float)
data class Weather(val temp: Float, val pressure: Float, val humidity: Float)
data class Wind(val speed: Float, val deg: Int)
data class Cloud(val cloudiness: Int)