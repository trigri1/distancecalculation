package com.test.data.distance.repository

import com.test.data.distance.model.CustomerModel
import io.reactivex.Single

interface DistanceRepository {
    fun getCustomers(distanceInKm: Int): Single<List<CustomerModel>>
}