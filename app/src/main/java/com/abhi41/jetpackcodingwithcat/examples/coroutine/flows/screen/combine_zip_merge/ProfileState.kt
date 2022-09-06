package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge

import com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen.combine_zip_merge.model.Post

data class ProfileState(
    val profilePicUrl: String? = null,
    val username: String? = null,
    val description: String? = null,
    val posts: List<Post> = emptyList(),
)
