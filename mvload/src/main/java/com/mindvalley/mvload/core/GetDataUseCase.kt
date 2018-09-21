package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import com.mindvalley.mvload.core.inmemory.InMemoryRepoImpl
import com.mindvalley.mvload.core.remote.RemoteRepoImpl
import com.mindvalley.mvload.core.mapper.StreamMapper
import io.reactivex.Observable


class GetDataUseCase<DataType>(executor: ExecutionThread, private val streamRepoImpl: StreamRepoImpl<DataType>,
                               private val mapper: StreamMapper<ByteArray, DataType>) : ObservableUseCase<DataType, RequestData>(executor) {
    override fun buildUseCaseObservable(params: RequestData?): Observable<DataType> {

        return streamRepoImpl.getStream(params!!, mapper)
    }


}