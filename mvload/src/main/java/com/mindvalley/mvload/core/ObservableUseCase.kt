package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class ObservableUseCase<T,in Params> constructor(private val execution: ExecutionThread) {


    private val disposables = CompositeDisposable()
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(observer: DisposableObserver<T>, params: Params? = null) {

        val observable = this.buildUseCaseObservable(params = params)
                .subscribeOn(execution.subscribeScheduler)
                .observeOn(execution.observerScheduler)
        addDisposable(observable.subscribeWith(observer))
    }

    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }


}
