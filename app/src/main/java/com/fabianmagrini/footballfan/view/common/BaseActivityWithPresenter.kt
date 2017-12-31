package com.fabianmagrini.footballfan.view.common

import android.support.v7.app.AppCompatActivity
import com.fabianmagrini.footballfan.presenter.Presenter

abstract class BaseActivityWithPresenter : AppCompatActivity() {

    abstract val presenter: Presenter

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}