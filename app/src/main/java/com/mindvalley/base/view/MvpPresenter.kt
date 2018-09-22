package com.mindvalley.base.view

interface MvpPresenter<V : MvpView> {


    fun onAttach(view: V)

    fun onResume()

    fun onDetach()

}