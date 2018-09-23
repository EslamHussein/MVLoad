package com.mindvalley.mvload.core


internal const val CONNECTION_READ_TIMEOUT = 10000
internal const val CONNECTION_TIMEOUT = 15000

enum class Method(val method: String) {
    GET("GET"),
    POST("POST")
}

open class RequestData(val url: String, val method: Method = Method.GET,
                       val urlParams: Map<String, String>? = null, val headers: Map<String, String>? = null)