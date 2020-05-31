package com.test.data.distance.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.test.data.base.model.ResponseModel
import com.test.data.distance.client.DistanceCalculator

@JsonClass(generateAdapter = true)
data class CustomerResponseModel(
    @Json(name = "user_id") val userId: Int? = null,
    val name: String? = null,
    val latitude: Double? = null,
    val longitude: Double? = null
) : ResponseModel() {
    override fun map(): CustomerModel {
        return CustomerModel(
            userId ?: 0,
            name.orEmpty(),
            DistanceCalculator.calculateDistance(latitude ?: 0.0, longitude ?: 0.0)
        )
    }
}