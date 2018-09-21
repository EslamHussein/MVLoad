package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.inmemory.InMemoryRepo
import com.mindvalley.mvload.core.remote.RemoteRepo
import com.mindvalley.mvload.core.mapper.StreamMapper
import io.reactivex.Observable

class StreamRepoImpl<DataType>(private val remote: RemoteRepo, private val inMemory: InMemoryRepo) {

    fun getStream(params: RequestData, mapper: StreamMapper<ByteArray, DataType>): Observable<DataType> {

        return inMemory.getCached(params.url).switchIfEmpty(remote.getStream(params).doOnNext {
            inMemory.put(params.url, it)
        }).map {
            mapper.map(it)
        }

    }

}