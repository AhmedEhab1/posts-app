package com.stc.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts_list")
data class PostsResponse(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val original_title : String,
    val poster_path: String,
)