package com.fabianmagrini.footballfan.view.main

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.fabianmagrini.footballfan.R
import com.fabianmagrini.footballfan.model.Post
import com.fabianmagrini.footballfan.view.common.ItemAdapter
import com.fabianmagrini.footballfan.view.common.bindView

class PostItemAdapter(
        val post: Post,
        val clicked: (Post) -> Unit
) : ItemAdapter<PostItemAdapter.ViewHolder>(R.layout.item_post) {

    override fun onCreateViewHolder(itemView: View) = ViewHolder(itemView)

    override fun ViewHolder.onBindViewHolder() {
        itemView.setOnClickListener { clicked(post) }
        titleView.text = post.title
        subTitleView.text = "${post.age} (${post.feed})"
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val titleView by bindView<TextView>(R.id.title)
        val subTitleView by bindView<TextView>(R.id.subtitle)
    }
}