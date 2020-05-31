package com.test.data.distance.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.test.data.base.model.MappedList
import com.test.data.distance.model.CustomerModel
import com.test.data.distance.repository.DistanceRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetGuestsListUseCaseTest {

    @Mock
    private lateinit var repository: DistanceRepository

    private lateinit var getGuestsListUseCaseTest: GetGuestsListUseCase

    val distance = 100

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        getGuestsListUseCaseTest = GetGuestsListUseCase(repository)
    }

    @Test
    fun `WHEN customers list has customers with in 100 km THEN return SUCCESS`() {
        val gustsList = getGuestsList(20.0)
        val mappedList = MappedList(gustsList)
        whenever(repository.getGuestsList(distance)).thenReturn(Single.just(gustsList))
        val testObserver = getGuestsListUseCaseTest.get(GetGuestsListUseCase.Args(distance)).test()

        testObserver.assertValue(mappedList)
    }

    @Test
    fun `WHEN customers list has NO customers with in 100 km THEN return ERROR`() {
        val error = Throwable()
        whenever(repository.getGuestsList(distance)).thenReturn(Single.error(error))
        val testObserver = getGuestsListUseCaseTest.get(GetGuestsListUseCase.Args(distance)).test()

        testObserver.assertError(error)
    }

    private fun getGuestsList(distance: Double): List<CustomerModel> {
        return listOf(
            getCustomer(1, "Christina", distance),
            getCustomer(2, "Alice", distance),
            getCustomer(3, "Jack", distance),
            getCustomer(4, "Charlie", distance)
        )
    }

    private fun getCustomer(id: Int, name: String, distance: Double): CustomerModel {
        return CustomerModel(id, name, distance)
    }
}