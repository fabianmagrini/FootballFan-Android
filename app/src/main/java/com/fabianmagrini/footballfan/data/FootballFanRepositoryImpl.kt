package com.fabianmagrini.footballfan.data

import com.fabianmagrini.footballfan.data.network.FootballFanApi
import com.fabianmagrini.footballfan.data.network.providers.retrofit
import com.fabianmagrini.footballfan.model.Post
import io.reactivex.Single

class FootballFanRepositoryImpl : FootballFanRepository {

    val api = retrofit.create(FootballFanApi::class.java)

    override fun getAllPosts(): Single<List<Post>> = api.getPosts(
            limit = elementsOnListLimit
    ).map {
        it.map(::Post)
    }

    companion object {
        const val elementsOnListLimit = 100
    }
}