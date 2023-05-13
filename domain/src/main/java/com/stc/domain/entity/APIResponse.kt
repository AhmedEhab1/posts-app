package com.stc.domain.entity

import androidx.room.Entity
@Entity
data class APIResponse(
    var page: Int,
    var results: List<PostsResponse>
)
