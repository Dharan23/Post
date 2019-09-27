package com.codeutility.post.ui.post.comment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codeutility.post.data.repository.PostRepository
import com.codeutility.post.ui.base.CustomLazy
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class CommentViewModel(
    private val postRepository: PostRepository
) : ViewModel() {

    fun getComments(id: Int) = viewModelScope.async {
        return@async postRepository.getPostComments(id)
    }
}