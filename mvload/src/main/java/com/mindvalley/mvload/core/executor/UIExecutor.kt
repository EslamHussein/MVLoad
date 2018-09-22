package com.mindvalley.mvload.core.executor

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class UIExecutor : ExecutionThread {
    override val subscribeScheduler: Scheduler
        get() = Schedulers.io()
    override val observerScheduler: Scheduler
        get() = AndroidSchedulers.mainThread()
}