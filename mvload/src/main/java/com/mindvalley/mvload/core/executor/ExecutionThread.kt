package com.mindvalley.mvload.core.executor

import io.reactivex.Scheduler

interface ExecutionThread {
    val subscribeScheduler: Scheduler
    val observerScheduler: Scheduler
}