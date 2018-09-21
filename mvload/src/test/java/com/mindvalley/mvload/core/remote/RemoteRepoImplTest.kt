package com.mindvalley.mvload.core.remote

import com.mindvalley.mvload.core.Method
import com.mindvalley.mvload.core.RequestData
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.net.MalformedURLException

class RemoteRepoImplTest {

    private lateinit var remoteRepo: RemoteRepo
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        remoteRepo = RemoteRepoImpl()
    }


    @Test
    fun checkRequestDataIsCorrect_wrong_url() {

        val requestData = RequestData("", Method.GET, null, null)


        val result = remoteRepo.getStream(requestData).test()

        result.assertError(MalformedURLException::class.java)


    }


    @Test
    fun checkRequestDataIsCorrect_correct_url() {

        val requestData = RequestData("https://www.google.com/", Method.GET, null, null)

        val result = remoteRepo.getStream(requestData).test()

        result.assertComplete()

    }


}