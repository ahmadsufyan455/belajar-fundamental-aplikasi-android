package com.fyndev.githubuser.detail

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.contentValuesOf
import androidx.lifecycle.ViewModelProvider
import coil.load
import coil.transform.CircleCropTransformation
import com.fyndev.githubuser.R
import com.fyndev.githubuser.adapter.ViewPagerAdapter
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.database.DatabaseContract
import com.fyndev.githubuser.database.DatabaseContract.UserColumn.Companion.CONTENT_URI
import com.fyndev.githubuser.database.UserHelper
import com.fyndev.githubuser.databinding.ActivityDetailUserBinding
import com.fyndev.githubuser.viewmodel.DetailUserViewModel

class DetailUserActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var userHelper: UserHelper
    private lateinit var uriWithId: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // get data object from intent with parcelable
        val dataUser = intent.getParcelableExtra<User>(EXTRA_DETAIL)
        val id = dataUser?.id
        val username = dataUser?.login
        val imgPhoto = dataUser?.avatar_url

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

        // open database
        userHelper = UserHelper.getInstance(applicationContext)
        userHelper.open()

        // set favorite user
        var statusFavorite = false
        setStatusFavorite(statusFavorite)
        binding.btnFavorite.setOnClickListener {
            if (!statusFavorite) {
                val values = contentValuesOf(
                        DatabaseContract.UserColumn._ID to id,
                        DatabaseContract.UserColumn.USERNAME to username,
                        DatabaseContract.UserColumn.AVATAR to imgPhoto
                )
                contentResolver.insert(CONTENT_URI, values)
                statusFavorite = !statusFavorite
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, "Add to favorite", Toast.LENGTH_SHORT).show()
            } else {
                uriWithId = Uri.parse("$CONTENT_URI/$id")
                contentResolver.delete(uriWithId, null, null)
                statusFavorite = !statusFavorite
                setStatusFavorite(statusFavorite)
                Toast.makeText(this, "Remove from favorite", Toast.LENGTH_SHORT).show()
            }
        }

        // database state
        val cursor: Cursor = userHelper.queryById(id.toString())
        if (cursor.moveToNext()) {
            statusFavorite = true
            setStatusFavorite(statusFavorite)
        }

        // share user
        binding.btnShare.setOnClickListener {
            val intent = Intent()
            intent.action = Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT, "He is an extraordinary user, his name is: ${dataUser?.login}")
            intent.type = "text/plain"
            startActivity(Intent.createChooser(intent, "Share to:"))
        }

        binding.icBack.setOnClickListener { finish() }

        // setup viewpager with tabLayout
        val viewPagerAdapter = ViewPagerAdapter(this, supportFragmentManager)
        viewPagerAdapter.username = dataUser?.login
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun setStatusFavorite(statusFavorite: Boolean) {
        if (statusFavorite) {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite)
        } else {
            binding.btnFavorite.setImageResource(R.drawable.ic_favorite_border)
        }
    }
}