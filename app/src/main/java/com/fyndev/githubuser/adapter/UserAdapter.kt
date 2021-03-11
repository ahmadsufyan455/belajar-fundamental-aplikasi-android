package com.fyndev.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.fyndev.githubuser.data.User
import com.fyndev.githubuser.databinding.ItemUserBinding
import com.fyndev.githubuser.detail.DetailUserActivity
import java.util.*
import kotlin.collections.ArrayList

class UserAdapter(listUser: ArrayList<User>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>(), Filterable {

    private val searchList = ArrayList<User>(listUser)
    private val mainList = listUser

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(mainList[position])
    }

    override fun getItemCount(): Int = mainList.size

    class ViewHolder(private val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: User) {
            binding.tvUsername.text = user.userName
            binding.imgPhoto.load(user.photo) {
                crossfade(true)
                crossfade(1000)
            }

            itemView.setOnClickListener {
                val moveToDetail = Intent(itemView.context, DetailUserActivity::class.java).apply {
                    putExtra(DetailUserActivity.EXTRA_DETAIL, user)
                }
                it.context.startActivity(moveToDetail)
            }
        }
    }

    // filter recyclerview with searchView
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence): FilterResults {
                val filteredList = ArrayList<User>()
                if (constraint.isBlank() or constraint.isEmpty()) {
                    filteredList.addAll(searchList)
                } else {
                    val filterPattern = constraint.toString().toLowerCase(Locale.ROOT).trim()
                    searchList.forEach {
                        if (it.userName.toLowerCase(Locale.ROOT).contains(filterPattern)) {
                            filteredList.add(it)
                        }
                    }
                }

                val result = FilterResults()
                result.values = filteredList

                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                mainList.clear()
                mainList.addAll(results?.values as List<User>)
                notifyDataSetChanged()
            }
        }
    }
}