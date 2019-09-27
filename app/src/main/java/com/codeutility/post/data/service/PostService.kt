package com.codeutility.post.data.network.service

import com.codeutility.post.data.network.response.Comment
import com.codeutility.post.data.network.response.Post
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

const val BASE_URL = "https://jsonplaceholder.typicode.com"

interface PostService {

    @GET("posts")
    fun getAllPosts(): Deferred<List<Post>>

    @GET("comments")
    fun getAllComments(
        @Query("postId") postId: Int
    ): Deferred<List<Comment>>

    companion object {
        operator fun invoke(): PostService {
            val okHttpClient = OkHttpClient.Builder()
                .connectTimeout(30L, TimeUnit.SECONDS)
                .readTimeout(30L, TimeUnit.SECONDS)
                .build()

            return Retrofit.Builder()
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build()
                .create(PostService::class.java)
        }
    }
}