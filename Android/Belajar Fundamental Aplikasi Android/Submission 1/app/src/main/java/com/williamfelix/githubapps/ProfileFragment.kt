package com.williamfelix.githubapps

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.williamfelix.githubapps.databinding.FragmentProfileBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class ProfileFragment : Fragment() {
    private lateinit var binding: FragmentProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentProfileBinding.inflate(layoutInflater)
        //val myStr = arguments.getString("username")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    companion object {
        private const val TAG = "ProfileFragment"
        var USERNAME = "username"
    }

    private fun findUser(query: String) {
        val client = ApiConfig.getApiService().getDetailUser(query)
        client.enqueue(object : Callback<UserDetailResponse> {
            override fun onResponse(
                call: Call<UserDetailResponse>,
                response: Response<UserDetailResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserDetail(responseBody)
                        showLoading(false)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<UserDetailResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun setUserDetail(user:UserDetailResponse){
        Glide.with(binding.imagesGithub)
            .load(user.avatarUrl)
            .centerCrop()
            .circleCrop()
            .into(binding.imagesGithub)
        binding.nameGithub.text = user.name ?: "-"
        binding.usernameGithub.text = user.login
        binding.follower.text =  StringBuilder().append(user.followers).append(" Follower")
        binding.following.text =  StringBuilder().append(user.following).append(" Following")
        binding.repo.text = StringBuilder().append(user.publicRepos).append(" Repository")
        binding.company.text = StringBuilder().append("Company : ").append(user.company ?: "-")
        binding.location.text = StringBuilder().append("Location : ").append(user.location ?: "-")
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }
}