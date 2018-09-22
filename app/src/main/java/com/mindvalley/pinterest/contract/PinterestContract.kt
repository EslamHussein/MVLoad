package com.mindvalley.pinterest.contract

import com.mindvalley.base.presenter.BasePresenter
import com.mindvalley.base.view.MvpView
import com.mindvalley.pinterest.model.dto.PinterestItem

interface PinterestContract {

    interface PinterestView : MvpView {
        fun showLoading()
        fun hideLoading()
        fun showSearchResultSuccess(projects: List<PinterestItem>, firstVisibleCellIndex: Int = 0)
        fun showSearchResultFailure(msg: String)
    }

    abstract class PinterestPresenter : BasePresenter <PinterestView>() {
        abstract fun getPinterestList(pageNumber: Int)
    }
}