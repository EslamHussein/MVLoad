package com.mindvalley.pinterest.view


import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.mindvalley.R
import com.mindvalley.base.view.BaseFragment
import com.mindvalley.pinterest.contract.PinterestContract
import com.mindvalley.pinterest.model.dto.PinterestItem
import kotlinx.android.synthetic.main.fragment_pinterest_list.*


private const val LIST_TAG = "LIST"
private const val FIRST_VISIBLE_CELL_TAG = "FIRST_VISIBLE_CELL"


class PinterestListFragment (): BaseFragment<PinterestContract.PinterestView, PinterestContract.PinterestPresenter>()
        , PinterestContract.PinterestView {


    private var pinterestAdapter: PinterestAdapter? = null

    private lateinit var viewManager: LinearLayoutManager


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pinterest_list, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewManager = LinearLayoutManager(context)

        pinterestAdapter = PinterestAdapter()

        pinterestRecyclerView.apply {
            layoutManager = viewManager
            adapter = pinterestAdapter
        }
        pinterestSwipeRefresh.isEnabled = false


    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (savedInstanceState != null) {
            val list = savedInstanceState.getParcelableArrayList<PinterestItem>(LIST_TAG)
            val firstVisibleCellIndex = savedInstanceState.getInt(FIRST_VISIBLE_CELL_TAG, 0)
            showSearchResultSuccess(list!!, firstVisibleCellIndex = firstVisibleCellIndex)
        } else {
            presenter.getPinterestList(pinterestAdapter?.getNextPageNumber() ?: 0)

        }

    }


    override fun onSaveInstanceState(outState: Bundle) {

        outState.putParcelableArrayList(LIST_TAG, pinterestAdapter?.getItems())
        outState.putInt(FIRST_VISIBLE_CELL_TAG, viewManager.findFirstVisibleItemPosition())
        super.onSaveInstanceState(outState)
    }


    override fun showLoading() {
        pinterestSwipeRefresh.isRefreshing = true
        if (pinterestAdapter?.itemCount ?: 0 <= 0) {
            loadingStatusTextView.text = getString(R.string.loading)
        } else {
            loadingStatusTextView.visibility = View.GONE
        }
    }

    override fun hideLoading() {
        pinterestSwipeRefresh.isRefreshing = false

    }

    override fun showSearchResultSuccess(projects: List<PinterestItem>, firstVisibleCellIndex: Int) {
        loadingStatusTextView.visibility = View.GONE

        pinterestAdapter?.addItems(projects)

    }

    override fun showSearchResultFailure(msg: String) {
        if (pinterestAdapter?.itemCount ?: 0 <= 0) {
            loadingStatusTextView.text = msg
            loadingStatusTextView.visibility = View.VISIBLE

        } else {

            Snackbar.make(view!!, msg, Snackbar.LENGTH_LONG).show()

        }

    }


}
