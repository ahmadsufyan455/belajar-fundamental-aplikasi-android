package com.fyndev.githubuser.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.fyndev.githubuser.adapter.UserAdapter
import com.fyndev.githubuser.databinding.FragmentFollowersBinding
import com.fyndev.githubuser.viewmodel.FollowersViewModel

class FollowersFragment : Fragment() {

    // singleton function to put data login
    companion object {
        private const val ARG_USERNAME = "username"
        fun getUsername(username: String): FollowersFragment {
            val fragment = FollowersFragment()
            val bundle = Bundle()
            bundle.putString(ARG_USERNAME, username)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentFollowersBinding

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentFollowersBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // get data login from singleton
        val username = arguments?.getString(ARG_USERNAME).toString()
        val userAdapter = UserAdapter()

        val viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory())[FollowersViewModel::class.java]
        viewModel.setData(username)
        viewModel.getData().observe(viewLifecycleOwner, { users ->
            if (users != null && users.size != 0) {
                userAdapter.setData(users)
            } else {
                binding.imgEmpty.visibility = View.VISIBLE
                binding.rvFollowers.visibility = View.INVISIBLE
            }
        })

        // setup recyclerview
        with(binding.rvFollowers) {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = userAdapter
        }
    }
}