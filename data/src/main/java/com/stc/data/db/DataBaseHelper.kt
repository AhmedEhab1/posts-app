package com.stc.data.db

import android.content.Context
import com.stc.domain.entity.PostsResponse

object DataBaseHelper {
    suspend fun savaPostsList(context: Context, list: List<PostsResponse>) {
        val postsResponse: List<PostsResponse> = list
        val db = PostsDataBase.getDatabase(context)
        db.postDao().insertPosts(postsResponse)
    }

    suspend fun getPostsFromDb(context: Context): List<PostsResponse> {
        val db = PostsDataBase.getDatabase(context)
        return db.postDao().getPosts()
    }
}