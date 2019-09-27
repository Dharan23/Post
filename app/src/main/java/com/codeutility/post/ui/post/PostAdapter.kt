package com.codeutility.post.ui.post

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codeutility.post.R
import com.codeutility.post.data.network.response.Post
import kotlinx.android.synthetic.main.layout_post.view.*

class PostAdapter(val listener: (View, Int) -> Unit) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    private var postList = ArrayList<Post>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_post,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = postList.get(position)
        holder.bind(post, listener)
    }

    fun updatePost(post: List<Post>) {
        if (post.isNotEmpty()) {
            postList.clear()
            postList.addAll(post)
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val postTitle: TextView = itemView.postTitle
        val postText: TextView = itemView.postText
        val commentBtn: LinearLayout = itemView.comment_box
        val likeBtn: LinearLayout = itemView.like_box

        fun bind(
            post: Post,
            listener: (View, Int) -> Unit
        ) {
            postTitle.text = post.title
            postText.text = post.body

            commentBtn.setOnClickListener {
                listener(it, post.id)
            }

            likeBtn.setOnClickListener {
                listener(it, post.id)
            }
        }
    }
}