package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.inmemory.InMemoryRepo
import com.mindvalley.mvload.core.remote.RemoteRepo
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class StreamRepoImplTest {


    private lateinit var streamRepo: StreamRepo

    @Mock
    private lateinit var remote: RemoteRepo
    @Mock
    private lateinit var inMemory: InMemoryRepo
    private lateinit var requestData: RequestData

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        streamRepo = StreamRepoImpl(remote, inMemory)
        requestData = RequestData("www.google.com/", Method.GET)

        Mockito.`when`(remote.getStream(requestData)).thenReturn(Observable.just(ByteArray(10)))

    }

    @Test
    fun verifyCalledRemoteMethod() {

        Mockito.`when`(inMemory.getCached(requestData.url)).thenReturn(Observable.empty())
        streamRepo.getStream(requestData)
        Mockito.verify(remote).getStream(requestData)

    }

    @Test
    fun getStreamFromRemote() {

        Mockito.`when`(inMemory.getCached(requestData.url)).thenReturn(Observable.empty())
        val testObservable = streamRepo.getStream(requestData).test()
        testObservable.assertComplete()

    }

    @Test
    fun getStreamFromCache() {

        Mockito.`when`(inMemory.getCached(requestData.url)).thenReturn(Observable.just(ByteArray(10)))
        val testObservable = streamRepo.getStream(requestData).test()
        testObservable.assertComplete()

    }


}