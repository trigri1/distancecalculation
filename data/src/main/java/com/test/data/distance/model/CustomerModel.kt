package com.test.data.distance.model

import com.test.data.base.model.MappedModel

data class CustomerModel(
    val userId: Int,
    val name: String,
    val distanceFromOffice: Double
) : MappedModel()