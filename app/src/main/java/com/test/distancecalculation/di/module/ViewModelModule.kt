package com.test.distancecalculation.di.module

import androidx.lifecycle.ViewModelProvider
import com.test.distancecalculation.utils.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ActivityModule::class])
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

//    @Binds
//    @IntoMap
//    @ViewModelKey(MainViewModel::class)
//    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

}