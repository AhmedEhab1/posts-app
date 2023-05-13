package com.stc.data.repo

import com.stc.data.remote.ApiService
import com.stc.domain.entity.PostsResponse
import com.stc.domain.repo.PostsRepo

class PostsRepoImpl(private val apiService: ApiService) : PostsRepo {
    override suspend fun getPostsFromRemote(page: Int): List<PostsResponse> =
        apiService.getPosts(page).results
}