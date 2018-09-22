package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import com.mindvalley.mvload.core.executor.UIExecutor
import com.mindvalley.mvload.core.inmemory.InMemoryRepoImpl
import com.mindvalley.mvload.core.mapper.JsonMapper
import com.mindvalley.mvload.core.mapper.StreamMapper
import com.mindvalley.mvload.core.remote.RemoteRepoImpl
import io.reactivex.Observable
import io.reactivex.observers.DisposableObserver


class GetDataUseCase<DataType>(executor: ExecutionThread, private val streamRepoImpl: StreamRepoImpl,
                               private val mapper: StreamMapper<ByteArray, DataType>)
    : ObservableUseCase<DataType, RequestData>(executor) {

    override fun buildUseCaseObservable(params: RequestData?): Observable<DataType> {
        if (params == null)
            throw IllegalArgumentException("Params can not be null")
        return streamRepoImpl.getStream(params).map { mapper.map(it) }
    }

}


//class JsonRequest {
//
//
//    private val request = GetDataUseCase(UIExecutor(), StreamRepoImpl(RemoteRepoImpl(),
//            InMemoryRepoImpl), JsonMapper())
//
//    fun <T> makeRequest(params: RequestData, onSuccess: (T) -> Unit, onFailure: (Throwable) -> Unit, onFinish: () -> Unit) {
//
//
//        request.execute(params = params, observer = object : DisposableObserver<String>() {
//            override fun onNext(t: String) {
//                onSuccess(t)
//            }
//
//            override fun onComplete() {
//                onFinish()
//            }
//
//            override fun onError(e: Throwable) {
//                onFailure(e)
//            }
//        })
//
//    }
//
//}


interface Callback<T> {
    fun onSuccess(result: T)
    fun onFailure(t: Throwable)
    fun onFinish()
}