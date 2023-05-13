package com.stc.data.remote

import com.stc.domain.entity.APIResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/popular")
    suspend fun getPosts(@Query("page") page : Int): APIResponse
}