package com.codeutility.post.ui.comment

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.codeutility.post.R
import com.codeutility.post.ui.base.ScopedActivity
import com.codeutility.post.ui.base.ViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_comment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class CommentActivity : ScopedActivity(), KodeinAware {
    private var postId: Int? = 0
    override val kodein by closestKodein()

    private val viewModelFactory: ViewModelProviderFactory by instance()

    private lateinit var commentAdapter: CommentAdapter
    private lateinit var viewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        init()
        bindUI()
    }

    private fun init() {
        setSupportActionBar(toolbar)
        supportActionBar?.apply {
            title = getString(R.string.comment)
            setDisplayHomeAsUpEnabled(true)
        }

        postId = intent.getIntExtra("postId", 0)
        commentAdapter = CommentAdapter { view: View, position: Int ->

        }

        comment_rv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        comment_rv.setHasFixedSize(true)
        comment_rv.adapter = commentAdapter

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(CommentViewModel::class.java)
    }

    private fun bindUI() = launch {
        val comments = viewModel.getComments(postId!!).await()

        comments.observe(this@CommentActivity, Observer {
            commentAdapter.updateComment(it)
        })
    }

}