package com.stc.domain.usecase

import com.stc.domain.repo.PostsRepo

class GetPosts(private val postsRepo: PostsRepo) {
    suspend operator fun invoke(page: Int) = postsRepo.getPostsFromRemote(page)
}
