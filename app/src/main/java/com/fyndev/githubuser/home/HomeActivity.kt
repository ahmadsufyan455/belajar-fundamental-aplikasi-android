package com.fyndev.githubuser.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.githubuser.adapter.UserAdapter
import com.fyndev.githubuser.databinding.ActivityHomeBinding
import com.fyndev.githubuser.viewmodel.UserViewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var userAdapter: UserAdapter
    private lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)

        userAdapter = UserAdapter()

        viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
        )[UserViewModel::class.java]

        showUser()
        filterUser()

        // setup recyclerview
        with(binding.rvUser) {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            setHasFixedSize(true)
            adapter = userAdapter
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.rvUser.visibility = View.GONE
                binding.imgNotFound.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
                query?.let { viewModel.setFilter(it) }

                val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                binding.searchView.clearFocus()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        binding.icLang.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }

    private fun showUser() {
        viewModel.setUser()
        viewModel.getUser().observe(this, { dataUser ->
            if (dataUser != null) {
                userAdapter.setData(dataUser)
                binding.progressBar.visibility = View.GONE
            }
        })
    }

    private fun filterUser() {
        viewModel.getFilter().observe(this@HomeActivity, { filterUser ->
            if (filterUser != null && filterUser.size != 0) {
                userAdapter.setData(filterUser)
                binding.progressBar.visibility = View.GONE
                binding.imgNotFound.visibility = View.GONE
                binding.rvUser.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
                binding.rvUser.visibility = View.GONE
                binding.imgNotFound.visibility = View.VISIBLE
            }
        })
    }
}