package com.williamfelix.githubapps

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.williamfelix.githubapps.databinding.FragmentFollowerBinding

class FollowerFragment : Fragment() {

    private lateinit var binding: FragmentFollowerBinding

    companion object {
        private const val TAG = "FollowerFragment"
        var USERNAME = "username"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFollowerBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val followerModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowerModel::class.java)
        val name = arguments?.getString(FollowerFragment.USERNAME)
        followerModel.findFollower(name?:"")
        followerModel.listFollower.observe(viewLifecycleOwner) { data ->
            val listFollowerAdapter = ListFollowerAdapter(data)
            binding.rvFollowers.adapter = listFollowerAdapter
        }

    }
}