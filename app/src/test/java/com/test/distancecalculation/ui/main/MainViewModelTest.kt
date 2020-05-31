package com.test.distancecalculation.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import com.test.data.base.model.EmptyModel
import com.test.data.base.model.MappedList
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.repository.DistanceRepositoryImpl.FileEmptyException
import com.test.data.distance.usecase.GetGuestsListUseCase
import com.test.data.distance.usecase.ReadFileUseCase
import com.test.data.rx.TestSchedulerProvider
import io.reactivex.Single
import io.reactivex.schedulers.TestScheduler
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.FileNotFoundException
import java.util.concurrent.TimeUnit

class MainViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private val testScheduler = TestScheduler()
    private lateinit var testSchedulerProvider: TestSchedulerProvider

    @Mock
    private lateinit var readFileUseCase: ReadFileUseCase

    @Mock
    private lateinit var getGuestsListUseCase: GetGuestsListUseCase

    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider(testScheduler)
    }

    @Test
    fun `WHEN viewModel is initialised THEN readFile SUCCESS`() {
        val observer: Observer<Unit> = mock()
        val observedValue = Unit
        val value = EmptyModel()

        whenever(readFileUseCase.get()).thenReturn(Single.just(value))

        initViewModel()

        viewModel.dataReady.observeForever(observer)

        verify(readFileUseCase).get()

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer).onChanged(observedValue)

        assertEquals(viewModel.dataReady.value, observedValue)
    }

    @Test
    fun `WHEN viewModel is initialised THEN readFile FAILED With unknown error`() {
        val observer: Observer<Unit> = mock()

        whenever(readFileUseCase.get()).thenReturn(Single.error(Throwable()))
        initViewModel()
        viewModel.dataReady.observeForever(observer)
        verify(readFileUseCase).get()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        assert(viewModel.error.value!!.isEmpty().not())
    }

    @Test
    fun `WHEN viewModel is initialised THEN readFile FAILED With FileNotFoundException`() {
        val observer: Observer<Unit> = mock()

        whenever(readFileUseCase.get()).thenReturn(Single.error(FileNotFoundException()))
        initViewModel()
        viewModel.dataReady.observeForever(observer)
        verify(readFileUseCase).get()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        assert(viewModel.error.value!! == "customer.txt file not found.")
    }

    @Test
    fun `WHEN viewModel is initialised THEN readFile FAILED With FileEmptyException`() {
        val observer: Observer<String> = mock()

        whenever(readFileUseCase.get()).thenReturn(Single.error(FileEmptyException()))
        initViewModel()
        viewModel.error.observeForever(observer)
        verify(readFileUseCase).get()
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        assert(viewModel.error.value!! == "Customers list is empty.")
    }

    @Test
    fun `WHEN onFindGuests THEN return guest list SUCCESS`() {
        val observer: Observer<List<CustomerModel>> = mock()
        val list = getGuestsList()
        val mappedList = getMappedList(list)
        val value = EmptyModel()

        whenever(readFileUseCase.get()).thenReturn(Single.just(value))
        whenever(getGuestsListUseCase.get(any())).thenReturn(Single.just(mappedList))

        initViewModel()
        viewModel.onFindGuests()
        viewModel.customersData.observeForever(observer)

        verify(getGuestsListUseCase).get(any())

        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        verify(observer).onChanged(list)

        assertEquals(viewModel.customersData.value, list)
    }


    @Test
    fun `WHEN onFindGuests THEN return guest list FAILED`() {
        val observer: Observer<String> = mock()
        val value = EmptyModel()

        whenever(readFileUseCase.get()).thenReturn(Single.just(value))
        whenever(getGuestsListUseCase.get(any())).thenReturn(Single.error(Throwable()))

        initViewModel()
        viewModel.onFindGuests()
        viewModel.error.observeForever(observer)

        verify(getGuestsListUseCase).get(any())
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        assert(viewModel.error.value!!.isNotEmpty())
    }


    private fun initViewModel() {
        viewModel = MainViewModel(readFileUseCase, getGuestsListUseCase, testSchedulerProvider)
    }

    private fun getGuestsList(): List<CustomerModel> {
        return listOf(
            getCustomer(1, "Christina", 102.0),
            getCustomer(2, "Alice", 59.5),
            getCustomer(3, "Jack", 150.0),
            getCustomer(4, "Charlie", 20.0)
        )
    }

    private fun getCustomer(id: Int, name: String, distance: Double): CustomerModel {
        return CustomerModel(id, name, distance)
    }

    private fun getMappedList(list: List<CustomerModel>): MappedList<CustomerModel> {
        return MappedList(list)
    }

}