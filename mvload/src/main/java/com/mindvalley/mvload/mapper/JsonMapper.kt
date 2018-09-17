package com.mindvalley.mvload.mapper

import java.io.*

class JsonMapper : StreamMapper<InputStream, String> {
    override fun map(inputSteam: InputStream): String {
        return inputSteam.bufferedReader().use {
            it.readText()
        }
    }
}