package com.test.distancecalculation.ui.main

import android.util.Log
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.usecase.GetCustomersUseCase
import com.test.data.rx.SchedulerProvider
import com.test.distancecalculation.ui.base.BaseViewModel
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getCustomersUseCase: GetCustomersUseCase,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

    init {
        getCustomersUseCase.get(GetCustomersUseCase.Args())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                handleSuccess(it.list)
            }, {

            })
            .addToDisposable()
    }

    private fun handleSuccess(list: List<CustomerModel>) {
        Log.e("DistanceCalculator", "=======================================================")
        Log.e("DistanceCalculator", "=======================================================")
        Log.e("DistanceCalculator", "=======================================================")
        Log.e("DistanceCalculator", "=======================================================")
        Log.e("DistanceCalculator", list.size.toString() + " ===== " + list.toString())

    }

    private fun handleError() {

    }
}