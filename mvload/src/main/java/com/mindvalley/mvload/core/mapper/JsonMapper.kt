package com.mindvalley.mvload.core.mapper


class JsonMapper : StreamMapper<ByteArray, String> {
    override fun map(inputStream: ByteArray): String {
        return String(inputStream)
    }
}