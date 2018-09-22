package com.mindvalley.mvload.core.inmemory

import io.reactivex.Observable
import java.io.InputStream

interface InMemoryRepo {

    fun isValidKey(key: String): Boolean

    fun getCached(key: String): Observable<ByteArray>

    fun getIfNotExpired(key: String, expiryTimeInMillis: Long): ByteArray?

    fun put(key: String?, value: ByteArray?)

    fun remove(key: String?)

    fun clear()
}