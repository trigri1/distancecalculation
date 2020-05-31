package com.test.data.distance.usecase

import com.test.data.base.model.MappedList
import com.test.data.base.usecase.UseCase
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.repository.DistanceRepository
import io.reactivex.Single
import javax.inject.Inject

class GetCustomersUseCase @Inject constructor(private val repository: DistanceRepository) :
    UseCase<GetCustomersUseCase.Args, MappedList<CustomerModel>> {

    override fun get(args: Args): Single<MappedList<CustomerModel>> {
        return repository.getCustomers(args.distanceInKm).map { MappedList(it) }
    }

    data class Args(val distanceInKm: Int = 100) : UseCase.Args()
}