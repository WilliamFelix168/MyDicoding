package com.williamfelix.githubapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.williamfelix.githubapps.databinding.FragmentFollowerBinding

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFollowerBinding.inflate(inflater,container,false)
        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowers.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollowers.addItemDecoration(itemDecoration)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followingModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingModel::class.java]
        followingModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        val name = arguments?.getString(USERNAME)
        followingModel.findFollowing(name?:"")
        followingModel.listFollowing.observe(viewLifecycleOwner) { data ->
            val listFollowingAdapter = ListFollowingAdapter(data)
            binding.rvFollowers.adapter = listFollowingAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        var USERNAME = "username"
    }
}