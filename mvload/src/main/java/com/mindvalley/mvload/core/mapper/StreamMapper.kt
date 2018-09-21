package com.mindvalley.mvload.core.mapper

interface StreamMapper<in Input, out Result> {

    fun map(inputStream: Input): Result
}