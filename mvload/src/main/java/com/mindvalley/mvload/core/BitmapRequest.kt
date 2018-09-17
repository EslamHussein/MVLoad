package com.mindvalley.mvload.core

import android.graphics.Bitmap
import com.mindvalley.mvload.mapper.BitmapMapper


interface Request {

    fun execute(params: BaseRequest.RequestData)
    fun cancel()
}

class BitmapRequest(private val onSuccess: (Bitmap) -> Unit = {}, private val onComplete: () -> Unit = {},
                    private val onError: (Exception) -> Unit = {}) : Request {

    private var request: BaseRequest<BaseRequest.RequestData, Bitmap, BitmapMapper>? = null

    override fun execute(params: BaseRequest.RequestData) {
//        request = BaseRequest(mapper = BitmapMapper(),
//                onSuccess = onSuccess, onError = onError, onComplete = onComplete)
        request?.execute(params)
    }

    override fun cancel() {
        request?.cancel(true)
    }

}