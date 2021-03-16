package com.fyndev.githubuser.adapter

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.fyndev.githubuser.R
import com.fyndev.githubuser.detail.FollowersFragment
import com.fyndev.githubuser.detail.FollowingFragment

class ViewPagerAdapter(private val context: Context, fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        private val TAB_TITLES = intArrayOf(R.string.follower, R.string.following)
    }

    private var _username: String? = null

    override fun getCount(): Int = TAB_TITLES.size

    override fun getItem(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = FollowersFragment.getUsername(_username.toString())
            1 -> fragment = FollowingFragment.getUsername(_username.toString())
        }

        return fragment as Fragment
    }

    override fun getPageTitle(position: Int): CharSequence = context.resources.getString(TAB_TITLES[position])

    // encapsulation properties
    internal var username: String?
        get() = _username
        set(value) {
            _username = value
        }
}