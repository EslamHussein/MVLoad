package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import com.mindvalley.mvload.core.mapper.StreamMapper
import io.reactivex.Observable


class GetDataUseCase<DataType>(executor: ExecutionThread, private val streamRepoImpl: StreamRepoImpl,
                               private val mapper: StreamMapper<ByteArray, DataType>)
    : ObservableUseCase<DataType, RequestData>(executor) {

    override fun buildUseCaseObservable(params: RequestData?): Observable<DataType> {
        if (params == null)
            throw IllegalArgumentException("Params can not be null")
        return streamRepoImpl.getStream(params).map { mapper.map(it) }
    }

}




