package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import com.mindvalley.mvload.core.inmemory.InMemoryRepoImpl
import com.mindvalley.mvload.core.remote.RemoteRepoImpl
import com.mindvalley.mvload.core.mapper.StreamMapper
import io.reactivex.Observable


class GetDataUseCase<DataType>(executor: ExecutionThread,
                               private val mapper: StreamMapper<ByteArray, DataType>) : ObservableUseCase<DataType, RequestData>(executor) {
    override fun buildUseCaseObservable(params: RequestData?): Observable<DataType> {

        val remote = RemoteRepoImpl()
        val inMemory = InMemoryRepoImpl

        return StreamRepoImpl<DataType>(remote = remote, inMemory = inMemory).getStream(params!!, mapper)


    }


}