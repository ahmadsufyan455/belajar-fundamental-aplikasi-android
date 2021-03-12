package com.fyndev.githubuser.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
import com.fyndev.githubuser.adapter.ViewPagerAdapter
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.databinding.ActivityDetailUserBinding

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityDetailUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get data object from intent with parcelable
        val dataUser = intent.getParcelableExtra<User>(EXTRA_DETAIL)

        binding.detailContainer.imgPhoto.load(dataUser?.photo) {
            crossfade(true)
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
        binding.tvDetail.text = dataUser?.name
        binding.detailContainer.tvName.text = dataUser?.name
        binding.detailContainer.tvUsername.text = dataUser?.userName
        binding.detailContainer.tvFollower.text = dataUser?.follower
        binding.detailContainer.tvFollowing.text = dataUser?.following
        binding.detailContainer.tvRepository.text = dataUser?.repository
        binding.detailContainer.tvLocation.text = dataUser?.location

        binding.icBack.setOnClickListener { finish() }

        // inflate viewpager with tabLayout
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}