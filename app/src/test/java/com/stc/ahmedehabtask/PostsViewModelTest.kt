package com.stc.ahmedehabtask

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stc.ahmedehabtask.viewModels.PostsViewModel
import com.stc.domain.entity.PostsResponse
import com.stc.domain.usecase.GetPosts
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test


import kotlinx.coroutines.test.*
import org.junit.After
import kotlin.math.log


@ExperimentalCoroutinesApi
class PostsViewModelTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: PostsViewModel
    private val getPostsUseCase = mockk<GetPosts>()
    private val testDispatcher = TestCoroutineDispatcher()
    private val testScope = TestCoroutineScope(testDispatcher)

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = PostsViewModel(getPostsUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        testScope.cleanupTestCoroutines() // cleanup coroutines
    }

    @Test
    fun `getPosts should update posts when successful`() = testDispatcher.runBlockingTest {
        // Given
        val expectedPosts = listOf(
            PostsResponse(1, "title1", "body1"),
            PostsResponse(2, "title2", "body2"),
            PostsResponse(3, "title3", "body3")
        )
        coEvery { getPostsUseCase(any()) } returns expectedPosts

        // When
        runBlocking {
            viewModel.getPosts(1)
            delay(1000)
        }

        // Then
        assertThat(viewModel.posts.value).isEqualTo(expectedPosts)
        assertThat(viewModel.errorMessage.value).isNull()
    }

    @Test
    fun `getPosts should update errorMessage when there is an exception`() = runBlockingTest {
        // Given
        val expectedError = "Something went wrong"
        coEvery { getPostsUseCase(any()) } throws Exception(expectedError)

        // When
        viewModel.getPosts(1)

        // Then
        assertThat(viewModel.posts.value).isNull()
        assertThat(viewModel.errorMessage.value).isEqualTo(expectedError)
    }
}