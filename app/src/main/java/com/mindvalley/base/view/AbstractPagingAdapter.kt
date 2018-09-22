package com.mindvalley.base.view

import android.support.v7.widget.RecyclerView

abstract class AbstractPagingAdapter<ItemType,
        VH : RecyclerView.ViewHolder>(var data: ArrayList<ItemType> = arrayListOf(),
                                      private val perPage: Int = 30) : RecyclerView.Adapter<VH>() {


    fun getNextPageNumber(): Int = (itemCount / perPage)

    fun addItems(newData: List<ItemType>) {
        val startPos = data.size
        data.addAll(newData)
        notifyItemRangeInserted(startPos, newData.size)
    }

    fun getItems(): ArrayList<ItemType> = data
    override fun getItemCount(): Int = data.count()


}
