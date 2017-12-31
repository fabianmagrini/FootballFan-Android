package com.fabianmagrini.footballfan.helpers

import com.fabianmagrini.footballfan.model.Post

object Example {
    val examplePost= Post("Wenger concerned about tension between Sanchez and his Arsenal team-mates", "Mirror")
    val examplePostList = listOf(
            examplePost,
            Post("Highest honour: Legendary keeper Buffon includes Kane in list of five greatest goalscorers he's ever faced", "Mirror"),
            Post("You hypocrite! Mourinho remembers what Klopp said about Pogba following Liverpool's £75m Van Dijk swoop", "Mirror")
    )
}