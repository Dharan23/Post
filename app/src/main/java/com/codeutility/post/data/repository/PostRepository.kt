package com.codeutility.post.data.repository

import androidx.lifecycle.LiveData
import com.codeutility.post.data.network.response.Comment
import com.codeutility.post.data.network.response.Post

interface PostRepository {

    suspend fun getAllPosts(): LiveData<List<Post>>
    suspend fun getPostComments(id: Int): LiveData<List<Comment>>
}