package com.test.data.distance.repository

import com.test.data.base.model.EmptyModel
import com.test.data.distance.model.CustomerModel
import io.reactivex.Single

interface DistanceRepository {
    fun readFile(): Single<EmptyModel>
    fun getGuestsList(distanceInKm: Int): Single<List<CustomerModel>>
}