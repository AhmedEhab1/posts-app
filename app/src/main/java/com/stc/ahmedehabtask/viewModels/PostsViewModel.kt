package com.stc.ahmedehabtask.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stc.domain.entity.PostsResponse
import com.stc.domain.usecase.GetPosts
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPosts
) : ViewModel() {
    private val _posts: MutableStateFlow<List<PostsResponse>?> = MutableStateFlow(null)
    val posts: StateFlow<List<PostsResponse>?> = _posts
    var errorMessage: MutableLiveData<String?> = MutableLiveData()

    fun getPosts(page: Int) {
        viewModelScope.launch {
            try {
                val posts = withContext(Dispatchers.IO) {
                    getPostsUseCase(page)
                }
                _posts.value = posts
            } catch (e: Exception) {
                errorMessage.value = e.message
            }
        }
    }
}