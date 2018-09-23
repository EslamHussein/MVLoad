package com.mindvalley.mvload.core.mapper

import org.json.JSONArray


class JSONArrayMapper : StreamMapper<ByteArray, JSONArray> {
    override fun map(inputStream: ByteArray): JSONArray {
        return JSONArray(String(inputStream))
    }
}