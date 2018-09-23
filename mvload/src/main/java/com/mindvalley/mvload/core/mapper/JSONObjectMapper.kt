package com.mindvalley.mvload.core.mapper

import org.json.JSONObject


class JSONObjectMapper : StreamMapper<ByteArray, JSONObject> {
    override fun map(inputStream: ByteArray): JSONObject {
        return JSONObject(String(inputStream))
    }
}