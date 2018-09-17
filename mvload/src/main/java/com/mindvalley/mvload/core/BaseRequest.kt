package com.mindvalley.mvload.core

import android.os.AsyncTask
import com.mindvalley.mvload.cache.inmemory.CacheManager
import com.mindvalley.mvload.core.listener.RequestListener
import com.mindvalley.mvload.mapper.StreamMapper
import java.net.URL
import java.io.*
import java.net.HttpURLConnection
import javax.net.ssl.HttpsURLConnection


internal const val CONNECTION_READ_TIMEOUT = 10000
internal const val CONNECTION_TIMEOUT = 15000

private val onNextStub: (T: Any) -> Unit = {}
private val onErrorStub: (Throwable) -> Unit = {}
private val onCompleteStub: () -> Unit = {}

enum class Method(val method: String) {
    GET("GET"),
    POST("POST")
}

class BaseRequest<RequestData : BaseRequest.RequestData, DataType,
        Mapper : StreamMapper<InputStream, DataType>>(private val mapper: Mapper, private val cacheManger: CacheManager<DataType>,
                                                      private val listener: RequestListener<DataType>?)
    : AsyncTask<RequestData, Void, BaseResponse<DataType>>() {


    private fun buildRequest(params: RequestData): BaseResponse<DataType> {
        val url = URL(params.url)
        var stream: InputStream? = null
        var connection: HttpURLConnection? = null
        var result: BaseResponse<DataType>
        try {
            connection = url.openConnection() as HttpURLConnection
            connection.readTimeout = CONNECTION_READ_TIMEOUT
            connection.connectTimeout = CONNECTION_TIMEOUT
            connection.requestMethod = params.method.method

            params.headers?.forEach { key, value ->
                connection.setRequestProperty(key, value)
            }

            connection.connect()

            val responseCode = connection.responseCode

            stream = connection.inputStream

            result = if (responseCode != HttpsURLConnection.HTTP_OK) {

                BaseResponse(code = responseCode, exception = IOException("HTTP error code: $responseCode"))

            } else {
                BaseResponse(code = responseCode, data = mapper.map(stream))
            }

        } catch (e: Exception) {

            result = BaseResponse(code = -1, exception = e)

        } finally {

            stream?.close()
            connection?.disconnect()

        }

        return result
    }

    override fun doInBackground(vararg params: RequestData): BaseResponse<DataType> {

        val requestParams = params[0]

        return if (cacheManger.get(requestParams.url) != null) {
            BaseResponse(code = 200, data = cacheManger.get(requestParams.url))
        } else {
            val x = buildRequest(requestParams)
            cacheManger.put(requestParams.url, x.data)
            return x
        }

    }


    override fun onPostExecute(result: BaseResponse<DataType>?) {
        super.onPostExecute(result)
        if (result?.data != null)
            listener?.onSuccess(result.data)
        else
            listener?.onError(result?.exception!!)
        listener?.onComplete()
    }


    open class RequestData(val url: String, val method: Method = Method.GET,
                           val urlParams: Map<String, String>? = null, val headers: Map<String, String>? = null) {

        companion object {

            fun forRequest(url: String, method: Method = Method.GET,
                           urlParams: Map<String, String>? = null, headers: Map<String, String>? = null): RequestData {
                return RequestData(url = url, method = method, urlParams = urlParams, headers = headers)
            }
        }
    }
}




