package com.test.distancecalculation.di

import com.test.distancecalculation.DistanceApp
import com.test.distancecalculation.di.module.ActivityModule
import com.test.distancecalculation.di.module.AppModule
import com.test.distancecalculation.di.module.RepositoryModule
import com.test.distancecalculation.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        AppModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        RepositoryModule::class]
)
interface AppComponent : AndroidInjector<DistanceApp> {

    override fun inject(instance: DistanceApp?)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: DistanceApp): Builder

        fun build(): AppComponent
    }
}