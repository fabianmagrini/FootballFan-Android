package com.fabianmagrini.footballfan.helpers

import com.fabianmagrini.footballfan.model.Post
import com.fabianmagrini.footballfan.view.main.MainView

class BaseMainView(
        var onShow: (items: List<Post>) -> Unit = {},
        val onShowError: (error: Throwable) -> Unit = {},
        override var refresh: Boolean = false
) : MainView {

    override fun show(items: List<Post>) {
        onShow(items)
    }

    override fun showError(error: Throwable) {
        onShowError(error)
    }
}