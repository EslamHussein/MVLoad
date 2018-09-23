package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import com.mindvalley.mvload.core.mapper.StreamMapper
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class GetDataUseCaseTest {


    @Mock
    private lateinit var executor: ExecutionThread
    @Mock
    private lateinit var streamRepoImpl: StreamRepoImpl
    @Mock
    private lateinit var mapper: StreamMapper<ByteArray, String>

    private lateinit var getDataUseCase: GetDataUseCase<String>

    private lateinit var testParam: RequestData

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        getDataUseCase = GetDataUseCase(executor, streamRepoImpl, mapper)

        val resultBytes = ByteArray(10)
        testParam = RequestData("www.google.com", method = Method.GET)

        // Given

        Mockito.`when`(streamRepoImpl.getStream(testParam))
                .thenReturn(Observable.just(resultBytes))


        Mockito.`when`(mapper.map(resultBytes))
                .thenReturn("{\"value\": \"Close\", \"onclick\": \"CloseDoc()\"} ")

    }


    @Test
    fun getDataSuccess() {

        // When
        val testObservable = getDataUseCase.buildUseCaseObservable(testParam).test()

        // Then
        testObservable.assertComplete()

    }

    @Test(expected = IllegalArgumentException::class)
    fun getDataFailureWithNullParams() {

        // When
        val testObservable = getDataUseCase.buildUseCaseObservable().test()

        // Then
        testObservable.assertComplete()

    }
}