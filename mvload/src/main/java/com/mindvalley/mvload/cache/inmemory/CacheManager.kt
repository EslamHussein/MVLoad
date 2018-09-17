package com.mindvalley.mvload.cache.inmemory

import android.util.LruCache
import java.util.concurrent.ConcurrentHashMap


class CacheManager<DataType>(maxSize: Int, private val mExpiryTimeInMillis: Long) {
    private val mLruCache: LruCache<String, DataType> = LruCache(maxSize)
    private val mTimeMap: MutableMap<String, Long> = ConcurrentHashMap()


    private fun isValidKey(key: String): Boolean {
        return mTimeMap.containsKey(key)
    }

    @Synchronized
    operator fun get(key: String): DataType? {
        return getIfNotExpired(key, System.currentTimeMillis() - mExpiryTimeInMillis)
    }

    @Synchronized
    fun getIfNotExpired(key: String, expiryTimeInMillis: Long): DataType? {
        if (!isValidKey(key)) {
            return null
        }
        if (mTimeMap[key]!! >= expiryTimeInMillis) {
            return mLruCache.get(key) as DataType
        } else {
            remove(key)
            return null
        }
    }

    @Synchronized
    fun put(key: String?, value: DataType?) {
        if (key != null && value != null) {
            mLruCache.put(key, value)
            mTimeMap[key] = System.currentTimeMillis()
        }
    }

    @Synchronized
    fun remove(key: String?) {
        if (key != null) {
            mLruCache.remove(key)
            mTimeMap.remove(key)
        }
    }

    @Synchronized
    fun clear() {
        mLruCache.evictAll()
        mTimeMap.clear()
    }
}

//
//class CacheManager<Key : DataType>(maxSize: Int, private val mExpiryTimeInMillis: Long = 0)
//    : LruCache<Key, DataType>(maxSize), Cache<DataType> {
//
//
//    private val mTimeMap: Map<String, Long> = ConcurrentHashMap()
//
//
//    private fun isValidKey(key: String?): Boolean {
//        return key != null && mTimeMap.containsKey(key)
//    }
//
//
//    override fun insert(key: String, data: DataType) {
//
//    }
//
//
//    override fun delete(key: String) {
//
//    }
//
//    override fun sizeOf(key: String?, data: DataType): Int {
//        val bytesCount: Int = data.toString().toByteArray().size
//        return bytesCount / 1024
//    }
//
//    override fun find(key: String): DataType {
//        TODO()
//    }
//
//    override fun clear() {
//
//    }
//}