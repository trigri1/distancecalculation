package com.test.data.distance.usecase

import com.nhaarman.mockito_kotlin.whenever
import com.test.data.base.model.EmptyModel
import com.test.data.distance.repository.DistanceRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ReadFileUseCaseTest {

    @Mock
    private lateinit var repository: DistanceRepository

    private lateinit var readFileUseCase: ReadFileUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        readFileUseCase = ReadFileUseCase(repository)
    }

    @Test
    fun `WHEN read file and file not empty THEN return SUCCESS`() {
        val emptyModel = EmptyModel()
        whenever(repository.readFile()).thenReturn(Single.just(emptyModel))
        val testObserver = readFileUseCase.get().test()

        testObserver.assertValue(emptyModel)
    }

    @Test
    fun `WHEN read file and file is empty THEN return ERROR`() {
        val error = Throwable()
        whenever(repository.readFile()).thenReturn(Single.error(error))
        val testObserver = readFileUseCase.get().test()

        testObserver.assertError(error)
    }
}