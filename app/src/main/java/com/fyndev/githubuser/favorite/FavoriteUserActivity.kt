package com.fyndev.githubuser.favorite

import android.database.ContentObserver
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.githubuser.adapter.UserAdapter
import com.fyndev.githubuser.database.DatabaseContract.UserColumn.Companion.CONTENT_URI
import com.fyndev.githubuser.databinding.ActivityFavoriteUserBinding
import com.fyndev.githubuser.helper.MappingHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userAdapter = UserAdapter()

        // setup recyclerview
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@FavoriteUserActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loadUserAsync()
            }
        }

        contentResolver.registerContentObserver(CONTENT_URI, true, myObserver)

        // get data
        loadUserAsync()

        binding.icBack.setOnClickListener { finish() }
    }

    private fun loadUserAsync() {
        GlobalScope.launch(Dispatchers.Main) {
            val deferredUser = async(Dispatchers.IO) {
                val cursor = contentResolver.query(CONTENT_URI, null, null, null, null)
                MappingHelper.mapCursorToArrayList(cursor)
            }

            val users = deferredUser.await()
            if (users.size > 0) {
                userAdapter.setData(users)
            } else {
                binding.rvUser.visibility = View.GONE
                binding.imgEmpty.visibility = View.VISIBLE
            }
        }
    }
}