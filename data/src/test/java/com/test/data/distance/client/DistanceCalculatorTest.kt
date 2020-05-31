package com.test.data.distance.client

import org.junit.Assert.assertNotNull
import org.junit.Test

class DistanceCalculatorTest {

    @Test
    fun `WHEN coordinates are more than 100 km away THEN return value greater than 100`() {
        val latitude = 52.833502
        val longitude = -8.522366

        val distance = DistanceCalculator.calculateDistance(latitude, longitude)
        assert(distance > 100)
    }

    @Test
    fun `WHEN coordinates are less than 100 km away THEN return value less than 100`() {
        val latitude = 53.761389
        val longitude = -7.2875

        val distance = DistanceCalculator.calculateDistance(latitude, longitude)
        assert(distance < 100)
    }

    @Test
    fun `WHEN coordinates are 0 than 100 km away THEN value is non null`() {
        val latitude = 0.0
        val longitude = 0.0

        val distance = DistanceCalculator.calculateDistance(latitude, longitude)
        assertNotNull(distance)
    }
}