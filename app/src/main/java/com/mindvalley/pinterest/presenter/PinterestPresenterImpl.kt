package com.mindvalley.pinterest.presenter

import com.mindvalley.pinterest.contract.PinterestContract
import com.mindvalley.pinterest.model.PinterestModel
import javax.inject.Inject

class PinterestPresenterImpl @Inject constructor(private val model: PinterestModel) : PinterestContract.PinterestPresenter() {
    override fun getPinterestList(pageNumber: Int, loadMore: Boolean) {

        view?.showLoading()
        model.getPinterestList { result, exception ->
            if (result != null)
                view?.showSearchResultSuccess(result, loadMore = loadMore)
            else {
                if (exception != null) {
                    view?.showSearchResultFailure(exception.localizedMessage)
                }
            }
            view?.hideLoading()
        }
    }
}