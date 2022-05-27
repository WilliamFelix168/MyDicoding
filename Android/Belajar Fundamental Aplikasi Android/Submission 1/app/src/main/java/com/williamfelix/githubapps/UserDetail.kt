package com.williamfelix.githubapps

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.williamfelix.githubapps.databinding.ActivityUserDetailBinding


class UserDetail : AppCompatActivity() {

    private lateinit var binding: ActivityUserDetailBinding

    companion object {
        const val USER = "user"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val user = intent.getParcelableExtra<User>(USER) as User
        Glide.with(binding.imagesGithub)
            .load(user.photo)
            .centerCrop()
            .circleCrop()
            .into(binding.imagesGithub)
        binding.nameGithub.text = user.name
        binding.usernameGithub.text = user.username
        binding.follower.text =  StringBuilder().append(user.followers).append(" Follower")
        binding.following.text =  StringBuilder().append(user.following).append(" Following")
        binding.repo.text = StringBuilder().append(user.repository).append(" Repository")
        binding.company.text = StringBuilder().append("Company : ").append(user.company)
        binding.location.text = StringBuilder().append("Location : ").append(user.location)
    }
}



