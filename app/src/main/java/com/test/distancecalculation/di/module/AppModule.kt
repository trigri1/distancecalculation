package com.test.distancecalculation.di.module


import android.content.Context
import com.squareup.moshi.Moshi
import com.test.data.annotations.AppContext
import com.test.data.distance.client.FileReader
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

    @Singleton
    @Provides
    fun provideFilerReader(@AppContext context: Context, moshi: Moshi): FileReader {
        return FileReader(context, moshi)
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }
}