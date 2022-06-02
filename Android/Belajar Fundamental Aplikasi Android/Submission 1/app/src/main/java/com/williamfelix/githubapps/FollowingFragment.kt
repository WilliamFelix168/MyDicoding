package com.williamfelix.githubapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.williamfelix.githubapps.databinding.FragmentFollowingBinding

class FollowingFragment : Fragment() {
    private lateinit var binding: FragmentFollowingBinding

    companion object {
        private const val TAG = "FollowingFragment"
        var USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentFollowingBinding.inflate(inflater,container,false)
        val layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(context, layoutManager.orientation)
        binding.rvFollowing.addItemDecoration(itemDecoration)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followingModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowingModel::class.java)
        followingModel.isLoading.observe(viewLifecycleOwner) {
            showLoading(it)
        }
        val name = arguments?.getString(FollowingFragment.USERNAME)
        followingModel.findFollowing(name?:"")
        followingModel.listFollowing.observe(viewLifecycleOwner) { data ->
            val listFollowingAdapter = ListFollowingAdapter(data)
            binding.rvFollowing.adapter = listFollowingAdapter
        }

    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}