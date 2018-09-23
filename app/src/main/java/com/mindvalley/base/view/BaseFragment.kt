package com.mindvalley.base.view

import android.content.Context
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<V : MvpView, P : MvpPresenter<V>> : DaggerFragment() {

    @Inject
    open lateinit var presenter: P

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        presenter.onAttach(view = this as V)

    }

    override fun onResume() {
        super.onResume()

        presenter.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

}
