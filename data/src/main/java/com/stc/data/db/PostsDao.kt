package com.stc.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.stc.domain.entity.PostsResponse

@Dao
interface PostsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertPosts(product: List<PostsResponse>)

    @Query("SELECT * FROM posts_list")
    suspend fun getPosts(): List<PostsResponse>
}