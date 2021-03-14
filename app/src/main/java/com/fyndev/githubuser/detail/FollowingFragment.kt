package com.fyndev.githubuser.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.githubuser.adapter.UserAdapter
import com.fyndev.githubuser.databinding.FragmentFollowingBinding
import com.fyndev.githubuser.viewmodel.FollowingViewModel

class FollowingFragment : Fragment() {

    companion object {
        private const val ARG_USERNAME = "username"
        fun getUsername(username: String): FollowingFragment {
            val fragment = FollowingFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentFollowingBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = arguments?.getString(ARG_USERNAME).toString()
        val userAdapter = UserAdapter()

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowingViewModel::class.java]

        viewModel.setData(username)
        viewModel.getData().observe(this, { users ->
            if (users != null && users.size != 0) {
                userAdapter.setData(users)
            } else {
                binding.imgEmpty.visibility = View.VISIBLE
                binding.rvFollowing.visibility = View.INVISIBLE
            }
        })

        // setup recyclerview
        with(binding.rvFollowing) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            userAdapter.notifyDataSetChanged()
            adapter = userAdapter
        }
    }
}