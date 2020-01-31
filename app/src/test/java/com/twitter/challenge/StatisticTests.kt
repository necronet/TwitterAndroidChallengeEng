package com.twitter.challenge

import com.twitter.challenge.core.Statistic
import org.assertj.core.api.Assertions
import org.junit.Test

class StatisticTests {
    @Test
    fun testStandardDeviation() {
        val sd = Statistic.standardDeviation(listOf(16.83f, 16.83f, 11.15f, 14.2f, 9.88f, 19.19f))
        Assertions.assertThat("%.1f".format(sd)).isEqualTo("%.1f".format(3.6))
    }
}