package com.mindvalley.mvload

import android.graphics.Bitmap
import android.support.annotation.DrawableRes
import android.util.Log
import android.widget.ImageView
import com.mindvalley.mvload.core.GetDataUseCase
import com.mindvalley.mvload.core.RequestData
import com.mindvalley.mvload.core.executor.UIExecutor
import com.mindvalley.mvload.core.mapper.BitmapMapper
import io.reactivex.observers.DisposableObserver

fun ImageView.load(url: String, @DrawableRes placeHolder: Int? = null, @DrawableRes errorImage: Int? = null) {


    placeHolder?.also {
        this.setImageResource(placeHolder)

    }

//    if (placeHolder != null)
    val paramsImage = RequestData.forRequest(url = url)

    val requestImage = GetDataUseCase(UIExecutor(), BitmapMapper())
    requestImage.execute(params = paramsImage, observer = object : DisposableObserver<Bitmap>() {
        override fun onNext(t: Bitmap) {

            this@load.setImageBitmap(t)
        }

        override fun onComplete() {

        }

        override fun onError(e: Throwable) {
            if (errorImage != null)
                this@load.setImageResource(errorImage)
            Log.d("asd", e.localizedMessage)

        }

    })

//
//    val params = RequestData.forRequest(url = "http://pastebin.com/raw/wgkJgazE")
//
//    val request = GetDataUseCase<String>(UIExecutor(), JsonMapper())
//    request.execute(params = params, observer = object : DisposableObserver<String>() {
//        override fun onNext(t: String) {
//
//            Log.d("asd", t)
//        }
//
//        override fun onComplete() {
//
//        }
//
//        override fun onError(e: Throwable) {
//            Log.d("asd", e.localizedMessage)
//
//        }
//
//    })


}