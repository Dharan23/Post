package com.codeutility.post.ui.post

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeutility.post.R
import com.codeutility.post.ui.base.ScopedActivity
import com.codeutility.post.ui.base.ViewModelProviderFactory
import com.codeutility.post.ui.post.comment.CommentActivity
import com.codeutility.post.util.showToast
import kotlinx.android.synthetic.main.activity_post.*
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class PostActivity : ScopedActivity(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: ViewModelProviderFactory by instance()

    private lateinit var postAdapter: PostAdapter
    private lateinit var viewModel: PostViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)
        setSupportActionBar(toolbar)
        init()
        bindUI()
    }

    private fun init() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PostViewModel::class.java)
        postAdapter = PostAdapter { view: View, id: Int ->
            println(view.id)
            when (view.id) {
                R.id.comment_box -> {
                    val intent = Intent(this, CommentActivity::class.java)
                    intent.putExtra("postId", id)
                    startActivity(intent)
                }
                R.id.like_box -> {
                    showToast("You liked this post")
                }
            }
        }

        //Post Recycler
        post_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        post_rv.setHasFixedSize(true)
        post_rv.adapter = postAdapter
    }

    private fun bindUI() = launch {
        val posts = viewModel.postLiveData.await()

        posts.observe(this@PostActivity, Observer {
            postAdapter.updatePost(it)
        })
    }
}
