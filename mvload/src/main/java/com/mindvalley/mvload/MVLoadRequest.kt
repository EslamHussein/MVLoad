package com.mindvalley.mvload


import android.graphics.Bitmap
import com.mindvalley.mvload.core.GetDataUseCase
import com.mindvalley.mvload.core.Method


import com.mindvalley.mvload.core.RequestData
import com.mindvalley.mvload.core.StreamRepoImpl
import com.mindvalley.mvload.core.executor.UIExecutor
import com.mindvalley.mvload.core.inmemory.InMemoryRepoImpl
import com.mindvalley.mvload.core.mapper.*
import com.mindvalley.mvload.core.remote.RemoteRepoImpl
import org.json.JSONArray
import org.json.JSONObject


class MVLoadClint(url: String, private val method: Method = Method.GET, urlParams: Map<String, String>? = null,
                  headers: Map<String, String>? = null) {


    private val requestData= RequestData(url, method, urlParams, headers)


    fun asBitmap(callback: (Bitmap?, Throwable?) -> Unit): kotlin.Any {
        return GetDataUseCase(UIExecutor(), StreamRepoImpl(RemoteRepoImpl(),
                InMemoryRepoImpl), BitmapMapper()).execute(requestData, callback)
    }


    fun asJsonObject(callback: (JSONObject?, Throwable?) -> Unit): kotlin.Any {
        return GetDataUseCase(UIExecutor(), StreamRepoImpl(RemoteRepoImpl(),
                InMemoryRepoImpl), JSONObjectMapper()).execute(requestData, callback)
    }

    fun asJsonArray(callback: (JSONArray?, Throwable?) -> Unit): kotlin.Any {
        return GetDataUseCase(UIExecutor(), StreamRepoImpl(RemoteRepoImpl(),
                InMemoryRepoImpl), JSONArrayMapper()).execute(requestData, callback)
    }

    fun <T> asGeneric(mapper: StreamMapper<ByteArray, T>, callback: (T?, Throwable?) -> Unit) {

        return GetDataUseCase(UIExecutor(), StreamRepoImpl(RemoteRepoImpl(),
                InMemoryRepoImpl), mapper).execute(requestData, callback)

    }

}
