package com.mindvalley.mvload.core.remote

import com.mindvalley.mvload.core.RequestData
import io.reactivex.Observable

interface RemoteRepo {
    fun getStream(requestData: RequestData): Observable<ByteArray>
}