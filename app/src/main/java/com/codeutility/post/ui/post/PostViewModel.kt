package com.codeutility.post.ui.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeutility.post.data.repository.PostRepository
import com.codeutility.post.ui.base.CustomLazy
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    val postLiveData = viewModelScope.async {
        return@async postRepository.getAllPosts()
    }
}