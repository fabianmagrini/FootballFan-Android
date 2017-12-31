package com.fabianmagrini.footballfan.presenter

import com.fabianmagrini.footballfan.data.FootballFanRepository
import com.fabianmagrini.footballfan.data.applySchedulers
import com.fabianmagrini.footballfan.data.plusAssign
import com.fabianmagrini.footballfan.data.subscribeBy
import com.fabianmagrini.footballfan.view.main.MainView

class MainPresenter(
        val view: MainView,
        val repository: FootballFanRepository
) : BasePresenter() {

    fun onViewCreated() {
        loadPosts()
    }

    fun onRefresh() {
        loadPosts()
    }

    private fun loadPosts() {
        subscriptions += repository.getAllPosts()
                .applySchedulers()
                .doOnSubscribe { view.refresh = true }
                .doFinally { view.refresh = false }
                .subscribeBy(
                        onSuccess = view::show,
                        onError = view::showError
                )
    }
}