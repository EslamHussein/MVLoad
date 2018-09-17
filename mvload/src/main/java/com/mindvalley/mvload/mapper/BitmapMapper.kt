package com.mindvalley.mvload.mapper

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.InputStream

class BitmapMapper : StreamMapper<InputStream, Bitmap> {
    override fun map(inputSteam: InputStream): Bitmap {
        return BitmapFactory.decodeStream(inputSteam)
    }
}