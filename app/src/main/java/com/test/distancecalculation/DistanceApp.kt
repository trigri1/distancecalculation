package com.test.distancecalculation

import android.app.Application
import com.test.distancecalculation.di.AppInjector
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class DistanceApp : Application(), HasAndroidInjector {

    @Inject
    lateinit var dispatchAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        AppInjector.init(app = this)
    }

    override fun androidInjector(): AndroidInjector<Any> = dispatchAndroidInjector
}