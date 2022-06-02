package com.williamfelix.githubapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.williamfelix.githubapps.databinding.ItemRowDataBinding

class ListFollowingAdapter(private val listFollowing: List<FollowingResponseItem>) :RecyclerView.Adapter<ListUserAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(
        viewGroup: ViewGroup,
        viewType: Int
    ): ListUserAdapter.ListViewHolder {
        val binding =
            ItemRowDataBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ListUserAdapter.ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListUserAdapter.ListViewHolder, position: Int) {
        val (photo, name) = listFollowing[position]
        Glide.with(holder.itemView.context)
            .load(photo)
            .centerCrop()
            .circleCrop()
            .into(holder.binding.imgItemPhoto)

        holder.binding.tvItemName.text = name
        holder.binding.tvItemUsername.text = name
//        holder.itemView.setOnClickListener {
//            val userDetail = Intent(holder.itemView.context, UserDetail::class.java)
//            userDetail.putExtra("username",name)
//            holder.itemView.context.startActivity(userDetail)
//        }
    }

    override fun getItemCount(): Int = listFollowing.size

    class ListViewHolder(var binding: ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root)
}