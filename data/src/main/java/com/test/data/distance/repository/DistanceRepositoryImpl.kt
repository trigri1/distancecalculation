package com.test.data.distance.repository

import com.test.data.distance.client.FileReader
import com.test.data.distance.model.CustomerModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DistanceRepositoryImpl @Inject constructor(
    private val fileReader: FileReader
) : DistanceRepository {

    private var customersList = fileReader.readDataFromFile()

    override fun getCustomers(distanceInKm: Int): Single<List<CustomerModel>> {
        val filterList = customersList.filter {
            it.distanceFromOffice >= distanceInKm
        }
        return Single.just(filterList)
    }
}