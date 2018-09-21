package com.mindvalley.mvload.core.remote

import com.mindvalley.mvload.core.CONNECTION_READ_TIMEOUT
import com.mindvalley.mvload.core.CONNECTION_TIMEOUT
import com.mindvalley.mvload.core.RequestData
import com.mindvalley.mvload.core.mapper.BytesMapper
import io.reactivex.Observable
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class RemoteRepoImpl : RemoteRepo {
    override fun getStream(requestData: RequestData): Observable<ByteArray> {

        return Observable.create<ByteArray> {

            val url = URL(requestData.url)
            var stream: InputStream? = null
            var connection: HttpURLConnection? = null
            try {
                connection = url.openConnection() as HttpURLConnection
                connection.readTimeout = CONNECTION_READ_TIMEOUT
                connection.connectTimeout = CONNECTION_TIMEOUT
                connection.requestMethod = requestData.method.method

                requestData.headers?.forEach { key, value ->
                    connection.setRequestProperty(key, value)
                }

                connection.connect()
                stream = connection.inputStream

                if (connection.responseCode != HttpsURLConnection.HTTP_OK) {
                    it.onError(IOException("HTTP error code: ${connection.responseCode}"))
                } else {
                    it.onNext(BytesMapper().map(stream))
                }

            } catch (e: Exception) {

                it.onError(e)

            } finally {

                stream?.close()
                connection?.disconnect()

            }

            it.onComplete()

        }

    }
}