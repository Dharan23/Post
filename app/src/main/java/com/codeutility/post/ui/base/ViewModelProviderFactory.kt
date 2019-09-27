package com.codeutility.post.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.codeutility.post.data.repository.PostRepository
import com.codeutility.post.ui.post.PostViewModel
import com.codeutility.post.ui.comment.CommentViewModel
import java.lang.Exception

class ViewModelProviderFactory(
    private val postRepository: PostRepository
) : ViewModelProvider.NewInstanceFactory() {


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostViewModel::class.java))
            return PostViewModel(postRepository) as T
        else if (modelClass.isAssignableFrom(CommentViewModel::class.java))
            return CommentViewModel(postRepository) as T
        throw Exception("Unknown ViewModel class")
    }
}