package com.mindvalley.base.view

import android.support.v7.widget.RecyclerView
import android.support.v7.util.DiffUtil


abstract class AbstractPagingAdapter<ItemType,
        VH : RecyclerView.ViewHolder>(var data: ArrayList<ItemType> = arrayListOf(),
                                      private val perPage: Int = 30) : RecyclerView.Adapter<VH>() {


    fun getNextPageNumber(): Int = (itemCount / perPage)

    abstract fun addItems(newData: List<ItemType>)

    fun getItems(): ArrayList<ItemType> = data
    override fun getItemCount(): Int = data.count()


}
