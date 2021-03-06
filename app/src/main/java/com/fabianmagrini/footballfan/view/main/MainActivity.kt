package com.fabianmagrini.footballfan.view.main

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.Window
import android.widget.Toast;
import com.fabianmagrini.footballfan.R
import com.fabianmagrini.footballfan.data.FootballFanRepository
import com.fabianmagrini.footballfan.model.Post
import com.fabianmagrini.footballfan.presenter.MainPresenter
import com.fabianmagrini.footballfan.view.common.BaseActivityWithPresenter
import com.fabianmagrini.footballfan.view.common.bindToSwipeRefresh
import com.fabianmagrini.footballfan.view.common.toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivityWithPresenter(), MainView {

    override var refresh by bindToSwipeRefresh(R.id.swipeRefreshView)

    override val presenter by lazy { MainPresenter(this, FootballFanRepository.get()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        swipeRefreshView.setOnRefreshListener { presenter.onRefresh() }
        presenter.onViewCreated()
    }

    override fun show(items: List<Post>) {
        val categoryItemAdapters = items.map(this::createCategoryItemAdapter)
        recyclerView.adapter = MainListAdapter(categoryItemAdapters)
    }

    override fun showError(error: Throwable) {
        toast("Error: ${error.message}")
        error.printStackTrace()
    }

    private fun createCategoryItemAdapter(post: Post)
            = PostItemAdapter(post, { showPostDetail(post) })

    private fun showPostDetail(post: Post) {
        Toast.makeText(this, post.link, Toast.LENGTH_SHORT).show();


        val intent = Intent(Intent.ACTION_VIEW,
                Uri.parse(post.link))
        startActivity(intent)

    }
}
