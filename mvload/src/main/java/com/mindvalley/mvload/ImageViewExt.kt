package com.mindvalley.mvload

import android.util.Log
import android.widget.ImageView
import com.mindvalley.mvload.core.BaseRequest
import com.mindvalley.mvload.core.BitmapRequest

fun ImageView.load(url: String) {


    val params = BaseRequest.RequestData.forRequest(url = url)

    val request = BitmapRequest(onSuccess = {
        this.setImageBitmap(it)
    })

    request.execute(params)

}