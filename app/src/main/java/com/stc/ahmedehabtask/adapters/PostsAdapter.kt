package com.stc.ahmedehabtask.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stc.domain.entity.PostsResponse
import com.stc.ahmedehabtask.databinding.PostItemBinding

class PostsAdapter(private val data: MutableList<PostsResponse>,
                   private val listener: PostClickListener
) : RecyclerView.Adapter<PostsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PostItemBinding.inflate(inflater, parent, false)
        return PostsViewHolder(binding , listener)
    }

    override fun onBindViewHolder(holder: PostsViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item, position)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    interface PostClickListener {
        fun onPostItemClick(position: PostsResponse)
    }

    fun addItems(newItems: List<PostsResponse>) {
        data.addAll(newItems)
        notifyItemRangeChanged(if (data.isEmpty()) 0 else data.size - 1 , data.size)
    }
}
