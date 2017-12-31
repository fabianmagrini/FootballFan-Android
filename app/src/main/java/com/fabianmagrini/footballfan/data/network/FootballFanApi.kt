package com.fabianmagrini.footballfan.data.network

import com.fabianmagrini.footballfan.data.network.dto.PostDto
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballFanApi {

    @GET("posts")
    fun getPosts(
            @Query("limit") limit: Int?
    ): Single<List<PostDto>>
}