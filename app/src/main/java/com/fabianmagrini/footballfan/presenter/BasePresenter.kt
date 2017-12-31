package com.fabianmagrini.footballfan.presenter

import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter : Presenter {

    protected var subscriptions = CompositeDisposable()

    override fun onViewDestroyed() {
        subscriptions.dispose()
    }
}