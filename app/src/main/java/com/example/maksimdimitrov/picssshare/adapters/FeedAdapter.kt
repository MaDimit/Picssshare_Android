package com.example.maksimdimitrov.picssshare.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import com.example.maksimdimitrov.picssshare.R
import com.example.maksimdimitrov.picssshare.fragments.main_activity.FeedData
import com.example.maksimdimitrov.picssshare.utilities.FADE_DURATION
import com.example.maksimdimitrov.picssshare.utilities.loadImage
import kotlinx.android.synthetic.main.post_item.view.*

class FeedAdapter(feedData: FeedData) : RecyclerView.Adapter<FeedAdapter.PostVh>() {

    interface FeedAdapterListener{
        fun onInteraction(view: View, position: Int = -1)
    }

    val dataset = feedData.dataSet
    val user = feedData.user
    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVh {
        context = parent.context
        val v = LayoutInflater.from(context)
                .inflate(R.layout.post_item, parent, false)
        return PostVh(v)
    }

    override fun getItemCount() = dataset.size

    override fun onBindViewHolder(holder: PostVh, position: Int) {
        val post = dataset[position]
        holder.profilePic.loadImage(post.profilePic,context)
        holder.username.text = post.username
        holder.image.loadImage(post.image, context)
        holder.viewsText.text = "${post.views} views"
        holder.commentsText.text = "View all ${post.comments} comments"
        holder.currentUserPic.loadImage(user.profileUserPic, context)
        setScaleAnimation(holder.itemView,position)
    }

    private var lastPosition = -1

    private fun setScaleAnimation(view: View, position: Int) {
        if (position > lastPosition) {
            val anim = AlphaAnimation(0.0f, 1.0f)
            anim.duration = FADE_DURATION
            view.startAnimation(anim)
            lastPosition = position
        }
    }

    inner class PostVh(view : View) : RecyclerView.ViewHolder(view){
        val profilePic = view.profile_pic
        val username = view.username
        val optionsBtn = view.btn_options
        val image = view.image
        val likeBtn = view.btn_like
        val commentBtn = view.btn_comments
        val shareBtn = view.btn_share
        val viewsText = view.txt_views
        val commentsText = view.txt_comments
        val currentUserPic = view.current_user_profilepic
        val addComment = view.edittext_addcomment
        val postBtn = view.btn_post
        val bookmarkBtn = view.bnt_bookmark

        init {

        }
    }
}