package com.williamfelix.githubapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.williamfelix.githubapps.databinding.ItemRowDataBinding

class ListFollowerAdapter(private val listFollower: List<FollowerResponseItem>) :RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListUserAdapter.ListViewHolder {
        val binding =
            ItemRowDataBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListUserAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.ListViewHolder, position: Int) {
        val (photo, name) = listFollower[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .centerCrop()
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        holder.binding.tvItemName.text = name
        holder.binding.tvItemUsername.text = name
    }
    override fun getItemCount(): Int = listFollower.size
}