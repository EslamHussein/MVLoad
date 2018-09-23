package com.mindvalley.pinterest.view

import android.support.v7.util.DiffUtil
import com.mindvalley.pinterest.model.dto.PinterestItem

class PinterestDiffCallback(private val mOldEventList: List<PinterestItem>,
                            private val mNewEventList: List<PinterestItem>) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = mOldEventList.size


    override fun getNewListSize(): Int = mNewEventList.size


    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return mOldEventList[oldItemPosition].id == mNewEventList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = mOldEventList[oldItemPosition]
        val newEvent = mNewEventList[newItemPosition]

        return oldItem == newEvent
    }


}