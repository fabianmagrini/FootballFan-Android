package com.fabianmagrini.footballfan.model

import com.fabianmagrini.footballfan.data.network.dto.PostDto

data class Post(
        val link: String,
        val feed: String,
        val title: String,
        val age: String
){

    constructor(dto: PostDto) : this(
            link = dto.link,
            feed = dto.feed,
            title = dto.title,
            age = dto.age
    )
}