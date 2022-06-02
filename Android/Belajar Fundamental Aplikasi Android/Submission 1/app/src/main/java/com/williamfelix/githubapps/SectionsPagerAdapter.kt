package com.williamfelix.githubapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

class SectionsPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    var username: String = ""

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 ->{
                fragment = ProfileFragment()
                fragment.arguments = Bundle().apply {
                    putString(ProfileFragment.USERNAME, username)
                }
            }
            1 -> fragment = FollowerFragment()
            2 -> fragment = FollowingFragment()
        }
        return fragment as Fragment
    }
}