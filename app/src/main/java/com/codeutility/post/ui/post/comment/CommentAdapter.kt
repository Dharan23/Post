package com.codeutility.post.ui.post.comment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codeutility.post.R
import com.codeutility.post.data.network.response.Comment
import com.codeutility.post.data.network.response.Post
import kotlinx.android.synthetic.main.layout_comment.view.*
import kotlinx.android.synthetic.main.layout_post.view.*

class CommentAdapter(val listener: (View, Int) -> Unit) :
    RecyclerView.Adapter<CommentAdapter.PostViewHolder>() {

    private var commentList = ArrayList<Comment>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.layout_comment,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val comment = commentList.get(position)
        holder.bind(comment, listener)
    }

    fun updateComment(comment: List<Comment>) {
        if (comment.isNotEmpty()) {
            commentList.clear()
            commentList.addAll(comment)
            notifyDataSetChanged()
        }
    }

    class PostViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val commentName: TextView = itemView.commentName
        val commentText: TextView = itemView.commentText

        fun bind(
            comment: Comment,
            listener: (View, Int) -> Unit
        ) {
            commentName.text = comment.name
            commentText.text = comment.body
        }
    }
}