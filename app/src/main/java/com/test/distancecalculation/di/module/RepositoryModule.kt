package com.test.distancecalculation.di.module


import com.test.data.repository.DistanceRepository
import com.test.data.repository.DistanceRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [AppModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun bindDistanceRepository(repository: DistanceRepositoryImpl): DistanceRepository
}