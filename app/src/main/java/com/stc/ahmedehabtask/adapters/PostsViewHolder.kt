package com.stc.ahmedehabtask.adapters

import androidx.recyclerview.widget.RecyclerView
import com.stc.domain.entity.PostsResponse
import com.stc.ahmedehabtask.databinding.PostItemBinding

class PostsViewHolder(private val binding: PostItemBinding, private val listener: PostsAdapter.PostClickListener) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(model: PostsResponse, position: Int) {
        binding.title.text = model.original_title
        binding.holder.setOnClickListener{
            listener.onPostItemClick(model)
        }
    }
}