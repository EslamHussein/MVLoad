package com.mindvalley.mvload.cache.inmemory

interface Cache<DataType> {

    fun insert(key: String, data: DataType)
    fun delete(key: String)
    fun find(key: String): DataType
    fun clear()
}