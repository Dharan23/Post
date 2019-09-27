package com.codeutility.post.ui.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeutility.post.data.repository.PostRepository
import kotlinx.coroutines.async

class CommentViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    fun getComments(id: Int) = viewModelScope.async {
        return@async postRepository.getPostComments(id)
    }
}