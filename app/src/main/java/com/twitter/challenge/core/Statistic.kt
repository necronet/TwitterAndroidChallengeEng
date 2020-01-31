package com.twitter.challenge.core

import kotlin.math.pow
import kotlin.math.sqrt

object Statistic {

    fun standardDeviation(data : List<Float>) : Float {
        val mean = mean(data)
        val degreesFreedom = data.size - 1
        return sqrt(data.fold (0.0f, { acc, fl -> acc + (fl - mean).pow(2) }).div(degreesFreedom))
    }

    private fun mean(data : List<Float>) : Float = data.sum()/data.size

}