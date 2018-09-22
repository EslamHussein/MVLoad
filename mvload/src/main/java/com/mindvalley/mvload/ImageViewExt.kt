package com.mindvalley.mvload

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.mindvalley.mvload.core.RequestData
fun ImageView.load(url: String, @DrawableRes placeHolder: Int? = null, @DrawableRes errorImage: Int? = null) {


    placeHolder?.also {
        this.setImageResource(placeHolder)

    }

    val paramsImage = RequestData(url = url)

//    MVLoadRequest.getInstance().load(paramsImage).asBitmap { bitmap, throwable ->
//
//
//        if (bitmap != null)
//            this.setImageBitmap(bitmap)
//        else {
//            errorImage?.also {
//                this.setImageResource(it)
//            }
//        }
//    }

}