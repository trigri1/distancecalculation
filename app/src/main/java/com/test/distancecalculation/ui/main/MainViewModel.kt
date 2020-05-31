package com.test.distancecalculation.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.repository.DistanceRepositoryImpl.FileEmptyException
import com.test.data.distance.usecase.GetGuestsListUseCase
import com.test.data.distance.usecase.ReadFileUseCase
import com.test.data.rx.SchedulerProvider
import com.test.distancecalculation.ui.base.BaseViewModel
import java.io.FileNotFoundException
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val readFileUseCase: ReadFileUseCase,
    private val getGuestsListUseCase: GetGuestsListUseCase,
    schedulerProvider: SchedulerProvider
) : BaseViewModel(schedulerProvider) {

    private val _dataReady = MutableLiveData<Unit>()
    val dataReady: LiveData<Unit> = _dataReady

    private val _customersData = MutableLiveData<List<CustomerModel>>()
    val customersData: LiveData<List<CustomerModel>> = _customersData

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    init {
        readCustomerFile()
    }

    private fun readCustomerFile() {
        readFileUseCase.get().subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                _dataReady.postValue(Unit)
            }, {
                handleError(it)
            })
            .addToDisposable()
    }

    fun onFindGuests() {
        getGuestsListUseCase.get(GetGuestsListUseCase.Args())
            .subscribeOn(schedulerProvider.io())
            .observeOn(schedulerProvider.ui())
            .subscribe({
                handleSuccess(it.list)
            }, {
                handleError(it)
            })
            .addToDisposable()
    }

    private fun handleSuccess(list: List<CustomerModel>) {
        if (list.isNullOrEmpty()) {
            _error.postValue("There are no customers with in 100 km.")
        } else {
            _customersData.postValue(list)
        }
    }

    private fun handleError(throwable: Throwable) {

        when (throwable) {
            is FileNotFoundException -> {
                _error.postValue("customer.txt file not found.")
            }
            is FileEmptyException -> {
                _error.postValue("Customers list is empty.")
            }
            else -> {
                _error.postValue("Unknown error occurred.")
            }
        }

    }
}