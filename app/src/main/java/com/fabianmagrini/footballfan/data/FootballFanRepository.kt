package com.fabianmagrini.footballfan.data

import com.fabianmagrini.footballfan.model.Post
import io.reactivex.Single

interface FootballFanRepository {

    fun getAllPosts(): Single<List<Post>>

    companion object : Provider<FootballFanRepository>() {
        override fun creator() = FootballFanRepositoryImpl()
    }
}