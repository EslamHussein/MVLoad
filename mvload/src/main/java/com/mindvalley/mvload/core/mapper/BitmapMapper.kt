package com.mindvalley.mvload.core.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class BitmapMapper : StreamMapper<ByteArray, Bitmap> {
    override fun map(input: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(input, 0, input.size)
    }
}