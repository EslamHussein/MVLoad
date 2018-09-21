package com.mindvalley.mvload.core.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapMapper : StreamMapper<ByteArray, Bitmap> {
    override fun map(bytes: ByteArray): Bitmap {

        return BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
    }
}