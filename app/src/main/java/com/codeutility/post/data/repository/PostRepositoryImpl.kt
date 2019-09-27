package com.codeutility.post.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.codeutility.post.data.network.response.Comment
import com.codeutility.post.data.network.response.Post
import com.codeutility.post.data.network.service.PostService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PostRepositoryImpl(
    private val postService: PostService
) : PostRepository {

    private val postLiveData = MutableLiveData<List<Post>>()
    private val commentLiveData = MutableLiveData<List<Comment>>()

    override suspend fun getAllPosts(): LiveData<List<Post>> {
        return withContext(Dispatchers.IO) {
            val fetchedPost = postService.getAllPosts().await()
            postLiveData.postValue(fetchedPost)
            return@withContext postLiveData
        }
    }

    override suspend fun getPostComments(id: Int): LiveData<List<Comment>> {
        return withContext(Dispatchers.IO) {
            val fetchedComments = postService.getAllComments(id).await()
            commentLiveData.postValue(fetchedComments)
            return@withContext commentLiveData
        }
    }
}