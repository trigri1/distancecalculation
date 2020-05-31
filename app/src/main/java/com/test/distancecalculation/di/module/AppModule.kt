package com.test.distancecalculation.di.module


import android.content.Context
import com.test.distancecalculation.di.annotations.AppContext
import com.test.data.rx.AppSchedulerProvider
import com.test.data.rx.SchedulerProvider
import com.test.distancecalculation.DistanceApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    @AppContext
    fun provideContext(context: DistanceApp): Context {
        return context
    }

    @Singleton
    @Provides
    fun provideSchedulers(): SchedulerProvider {
        return AppSchedulerProvider()
    }
}