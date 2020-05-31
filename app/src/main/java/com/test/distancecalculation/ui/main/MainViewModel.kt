package com.test.distancecalculation.ui.main

import com.test.data.rx.SchedulerProvider
import com.test.distancecalculation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(schedulerProvider: SchedulerProvider) :
    BaseViewModel(schedulerProvider) {
}