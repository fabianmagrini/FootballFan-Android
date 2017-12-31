package com.fabianmagrini.footballfan.view.main

import com.fabianmagrini.footballfan.model.Post

interface MainView {
    var refresh: Boolean
    fun show(items: List<Post>)
    fun showError(error: Throwable)
}