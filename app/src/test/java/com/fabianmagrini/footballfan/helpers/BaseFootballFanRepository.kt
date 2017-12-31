package com.fabianmagrini.footballfan.helpers

import com.fabianmagrini.footballfan.data.FootballFanRepository
import com.fabianmagrini.footballfan.model.Post
import io.reactivex.Single

class BaseFootballFanRepository(
        val onGetCharacters: () -> Single<List<Post>>
) : FootballFanRepository {

    override fun getAllCharacters() = onGetCharacters()
}