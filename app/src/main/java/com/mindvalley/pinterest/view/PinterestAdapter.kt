package com.mindvalley.pinterest.view

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.mindvalley.R
import com.mindvalley.base.view.AbstractPagingAdapter
import com.mindvalley.pinterest.model.dto.PinterestItem

class PinterestAdapter : AbstractPagingAdapter<PinterestItem, PinterestAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.pinterest_item_view, parent, false)
        return ViewHolder(itemView)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindData(data[position])
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var usernameTextView: TextView = view.findViewById(R.id.usernameTextView)

        fun bindData(item: PinterestItem) {
            usernameTextView.text = item.user.name
        }
    }
}