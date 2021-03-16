package com.fyndev.githubuser.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.fyndev.githubuser.adapter.ViewPagerAdapter
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.databinding.ActivityDetailUserBinding
import com.fyndev.githubuser.viewmodel.DetailUserViewModel

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

        val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        )[DetailUserViewModel::class.java]

        dataUser?.let { viewModel.setData(it.login) }
        viewModel.getData().observe(this, { user ->
            if (user != null) {
                binding.detailContainer.imgPhoto.load(user.avatar_url) {
                    crossfade(true)
                    crossfade(1000)
                    transformations(CircleCropTransformation())
                }

                binding.tvDetail.text = user.name
                binding.detailContainer.tvName.text = user.name
                binding.detailContainer.tvUsername.text = user.login
                binding.detailContainer.tvFollower.text = user.followers
                binding.detailContainer.tvFollowing.text = user.following
                binding.detailContainer.tvRepository.text = user.public_repos
                binding.detailContainer.tvLocation.text = user.location

                binding.viewLoad.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            }
        })

        binding.icBack.setOnClickListener { finish() }

        // setup viewpager with tabLayout
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPagerAdapter.username = dataUser?.login
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }
}