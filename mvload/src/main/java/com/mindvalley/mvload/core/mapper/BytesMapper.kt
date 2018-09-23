package com.mindvalley.mvload.core.mapper

import java.io.ByteArrayOutputStream
import java.io.InputStream

class BytesMapper :StreamMapper<InputStream,ByteArray>{
    override fun map(inputStream: InputStream): ByteArray {

        val outputStream = ByteArrayOutputStream()
        inputStream.use { input ->
            outputStream.use { output ->
                input.copyTo(output)
            }
        }
        return outputStream.toByteArray()
    }

}