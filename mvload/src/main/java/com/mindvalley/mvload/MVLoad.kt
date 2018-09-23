package com.mindvalley.mvload

import com.mindvalley.mvload.core.config.CacheConfig

object MVLoad {

    private val maxMemory = (Runtime.getRuntime().maxMemory() / 1024).toInt()

    // Use 1/8th of the available memory for this memory cache.
    private val cacheSize = maxMemory / 8

    private const val MEMORY_EXPIRATION: Long = 60000

    var defaultCacheConfigration: CacheConfig = CacheConfig(cacheSize = cacheSize, inMemoryExpiration = MEMORY_EXPIRATION)
        private set

    fun init(cacheConfig: CacheConfig) {
        this.defaultCacheConfigration = cacheConfig
    }
}