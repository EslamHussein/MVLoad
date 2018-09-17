package com.mindvalley.mvload.core.listener

interface RequestListener<DataType> {

    fun onSuccess(result: DataType)
    fun onError(result: Exception)
    fun onComplete()


}