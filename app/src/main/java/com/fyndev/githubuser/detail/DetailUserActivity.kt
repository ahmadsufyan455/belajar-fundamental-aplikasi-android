package com.fyndev.githubuser.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import coil.transform.CircleCropTransformation
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

        binding.imgPhoto.load(dataUser?.photo) {
            crossfade(true)
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
        binding.tvDetail.text = dataUser?.name
        binding.tvName.text = dataUser?.name
        binding.tvUsername.text = dataUser?.userName
        binding.tvFollower.text = dataUser?.follower
        binding.tvFollowing.text = dataUser?.following
        binding.tvRepository.text = dataUser?.repository
        binding.tvLocation.text = dataUser?.location
        binding.tvCompany.text = dataUser?.company

        binding.icBack.setOnClickListener { finish() }
    }
}