package com.mindvalley.mvload.core.inmemory

import android.support.v4.util.LruCache
import com.mindvalley.mvload.MVLoad
import io.reactivex.Observable
import java.util.concurrent.ConcurrentHashMap


object InMemoryRepoImpl : LruCache<String, ByteArray>(MVLoad.defaultCacheConfigration.cacheSize), InMemoryRepo {


    private val mExpiryTimeInMillis: Long = MVLoad.defaultCacheConfigration.inMemoryExpiration
    private var mTimeMap: MutableMap<String, Long> = ConcurrentHashMap()
    override fun isValidKey(key: String): Boolean {
        return mTimeMap.containsKey(key)
    }

    override fun sizeOf(key: String, value: ByteArray): Int {
        return value.size / 1024
    }

    @Synchronized
    override fun getCached(key: String): Observable<ByteArray> {

        return Observable.just(key).flatMap {

            val result = getIfNotExpired(it, System.currentTimeMillis() - mExpiryTimeInMillis)
            if (result == null)
                Observable.empty()
            else
                Observable.just(result)
        }

    }

    @Synchronized
    override fun getIfNotExpired(key: String, expiryTimeInMillis: Long): ByteArray? {
        if (!isValidKey(key)) {
            return null
        }
        return if (mTimeMap[key]!! >= expiryTimeInMillis) {
            get(key)
        } else {
            remove(key)
            null
        }
    }

    @Synchronized
    override fun put(key: String?, value: ByteArray?) {
        if (key != null && value != null) {
            put(key, value)
            mTimeMap[key] = System.currentTimeMillis()
        }
    }

    @Synchronized
    override fun remove(key: String?) {
        if (key != null) {
            remove(key)
            mTimeMap.remove(key)
        }
    }

    @Synchronized
    override fun clear() {
        evictAll()
        mTimeMap.clear()
    }
}
