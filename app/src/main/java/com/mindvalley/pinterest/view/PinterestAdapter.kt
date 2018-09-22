package com.mindvalley.pinterest.view

import android.content.Context
import android.graphics.Color
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.mindvalley.R
import com.mindvalley.base.view.AbstractPagingAdapter
import com.mindvalley.mvload.load
import com.mindvalley.pinterest.model.dto.PinterestItem
import de.hdodenhof.circleimageview.CircleImageView

class PinterestAdapter(private val context: Context?) : AbstractPagingAdapter<PinterestItem, PinterestAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.pinterest_item_view, parent, false)
        return ViewHolder(itemView)

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


        val item = data[position]

        holder.usernameTextView.text = context?.getString(R.string.username_placeholder, item.user.username)
        holder.pinterestImageView.load(item.urls.regular)
        holder.profileImageView.load(item.user.profile_image.small)
        holder.profileImageView.borderColor = Color.parseColor(item.color)
        holder.nameTextView.text = item.user.name

    }

    override fun addItems(newData: List<PinterestItem>) {
        val diffResult = DiffUtil.calculateDiff(PinterestDiffCallback(this.data, newData))

        this.data.clear()
        this.data.addAll(newData)

        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var usernameTextView: TextView = view.findViewById(R.id.usernameTextView)
        var pinterestImageView: ImageView = view.findViewById(R.id.pinterestImageView)
        var profileImageView: CircleImageView = view.findViewById(R.id.profileImageView)
        val nameTextView: TextView = view.findViewById(R.id.nameTextView)
    }
}