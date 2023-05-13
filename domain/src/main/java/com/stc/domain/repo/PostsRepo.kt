package com.stc.domain.repo

import com.stc.domain.entity.PostsResponse

interface PostsRepo {
    suspend fun getPostsFromRemote(page: Int): List<PostsResponse>
}