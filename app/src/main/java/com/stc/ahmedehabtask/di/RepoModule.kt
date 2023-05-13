package com.stc.ahmedehabtask.di

import com.stc.data.db.PostsDao
import com.stc.data.remote.ApiService
import com.stc.data.repo.PostsRepoImpl
import com.stc.domain.repo.PostsRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Provides
    fun provideRepo(apiService: ApiService): PostsRepo{
        return PostsRepoImpl(apiService)
    }
}