package com.test.data.distance.usecase

import com.test.data.base.model.EmptyModel
import com.test.data.base.usecase.NoArgUseCase
import com.test.data.distance.repository.DistanceRepository
import io.reactivex.Single
import javax.inject.Inject

class ReadFileUseCase @Inject constructor(private val repository: DistanceRepository) :
    NoArgUseCase<EmptyModel> {
    override fun get(): Single<EmptyModel> = repository.readFile()
}