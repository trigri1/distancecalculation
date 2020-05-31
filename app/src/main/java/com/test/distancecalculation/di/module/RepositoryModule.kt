package com.test.distancecalculation.di.module


import com.test.data.distance.repository.DistanceRepository
import com.test.data.distance.repository.DistanceRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [AppModule::class])
abstract class RepositoryModule {

    @Binds
    abstract fun bindDistanceRepository(repository: DistanceRepositoryImpl): DistanceRepository
}