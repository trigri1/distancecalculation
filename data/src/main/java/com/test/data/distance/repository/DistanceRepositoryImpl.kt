package com.test.data.distance.repository

import com.test.data.base.model.EmptyModel
import com.test.data.distance.client.FileReader
import com.test.data.distance.model.CustomerModel
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DistanceRepositoryImpl @Inject constructor(
    private val fileReader: FileReader
) : DistanceRepository {

    private lateinit var customersList: List<CustomerModel>

    override fun readFile(): Single<EmptyModel> {
        return fileReader.read().map {
            if (it.isNullOrEmpty()) {
                throw FileEmptyException()
            }
            customersList = it
            EmptyModel()
        }
    }

    override fun getGuestsList(distanceInKm: Int): Single<List<CustomerModel>> {
        val filterList = customersList.filter {
            it.distanceFromOffice <= distanceInKm
        }

        return Single.just(filterList)
    }

    class FileEmptyException() : Exception()
}