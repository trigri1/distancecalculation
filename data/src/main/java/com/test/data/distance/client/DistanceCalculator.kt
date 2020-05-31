package com.test.data.distance.client

import android.util.Log
import java.lang.Math.toRadians
import kotlin.math.*

const val EARTH_RADIUS = 6371.0

object DistanceCalculator {

    private val officeLatitude by lazy { 53.339428.toRadians() }
    private val officeLongitude by lazy { (-6.257664).toRadians() }

    fun calculateDistance(customerLatitude: Double, customerLongitude: Double): Double {

        val latInRadian = customerLatitude.toRadians()
        val longInRadian = customerLongitude.toRadians()

        val distance = EARTH_RADIUS * calculateCentralAngle(latInRadian, longInRadian)

        Log.e("DistanceCalculator", distance.toString())

        return distance
    }

    private fun calculateCentralAngle(latInRadian: Double, longInRadian: Double): Double {
        val value =
            getNumerator(latInRadian, longInRadian) / getDenominator(latInRadian, longInRadian)
        return atan(value)
    }

    private fun getNumerator(latitude: Double, longitude: Double): Double {
        val longDifference = getDifference(officeLongitude, longitude)
        val sqrtV1 = (cos(latitude) * sin(longDifference)).square()
        val sqrtV2 = (
                cos(officeLatitude) * sin(latitude)
                        - sin(officeLatitude) * cos(latitude) * cos(longDifference)).square()
        return sqrt(getSum(sqrtV1, sqrtV2))
    }

    private fun getDenominator(latitude: Double, longitude: Double): Double {
        val longDifference = getDifference(officeLongitude, longitude)
        val v1 = sin(officeLatitude) * sin(latitude)
        val v2 = cos(officeLatitude) * cos(latitude) * cos(longDifference)
        return v1 + v2
    }


    private fun getDifference(v1: Double, v2: Double): Double = v1 - v2
    private fun getSum(v1: Double, v2: Double) = v1 + v2
}

fun Double.toRadians(): Double = toRadians(this)


fun Double.square(): Double = pow(2)
