package com.codeutility.post.ui.post

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeutility.post.data.network.response.Post
import com.codeutility.post.data.repository.PostRepository
import com.codeutility.post.ui.base.CustomLazy
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PostViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    val postLiveData = MutableLiveData<List<Post>>()

    fun getAllPosts() = viewModelScope.launch {
        postLiveData.postValue(postRepository.getAllPosts().value)
    }
}