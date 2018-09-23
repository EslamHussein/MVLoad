package com.mindvalley.mvload.core

import com.mindvalley.mvload.core.executor.ExecutionThread
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableObserver


abstract class ObservableUseCase<T, in Params> constructor(private val execution: ExecutionThread) {


    private val disposables = CompositeDisposable()
    abstract fun buildUseCaseObservable(params: Params? = null): Observable<T>

    open fun execute(params: Params? = null, callback: (T?, Throwable?) -> Unit) {


        val observable = this.buildUseCaseObservable(params = params)
                .subscribeOn(execution.subscribeScheduler)
                .observeOn(execution.observerScheduler)

        val disposable = observable.subscribeWith(object : DisposableObserver<T>() {
            override fun onComplete() {

            }

            override fun onNext(t: T) {
                callback(t, null)
            }

            override fun onError(e: Throwable) {
                callback(null, e)

            }

        })
        addDisposable(disposable)
    }



    fun dispose() {
        disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }


}
