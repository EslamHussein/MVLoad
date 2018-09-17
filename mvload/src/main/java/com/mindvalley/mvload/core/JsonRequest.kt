package com.mindvalley.mvload.core

import com.mindvalley.mvload.cache.inmemory.CacheManager
import com.mindvalley.mvload.core.listener.RequestListener
import com.mindvalley.mvload.mapper.StreamMapper
import java.io.InputStream


class JsonRequest<DataType>(private val mapper: StreamMapper<InputStream, DataType>, private val cacheManger: CacheManager<DataType>) {

    private var request: BaseRequest<BaseRequest.RequestData, DataType, StreamMapper<InputStream, DataType>>? = null


    private var listener: RequestListener<DataType>? = null


    fun setListener(listener: RequestListener<DataType>) {
        this.listener = listener
    }

    fun execute(params: BaseRequest.RequestData) {
        request = BaseRequest(mapper = mapper, listener = listener, cacheManger = cacheManger)
        request?.execute(params)
    }

    fun cancel() {
        request?.cancel(true)
    }


}