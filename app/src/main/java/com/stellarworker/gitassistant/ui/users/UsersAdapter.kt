package com.stellarworker.gitassistant.ui.users

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class UsersAdapter(
    private val onItemClicked: ((userInfo: UserInfo) -> Unit)? = null
) : RecyclerView.Adapter<UserViewHolder>() {
    private val data = mutableListOf<UserInfo>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(parent, onItemClicked)

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun getItemCount() = data.size

    private fun getItem(position: Int) = data[position]

    @SuppressLint("NotifyDataSetChanged")
    fun clearData() {
        data.clear()
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(usersInfo: MainActivityDataset) {
        data.clear()
        data.addAll(usersInfo.users)
        notifyDataSetChanged()
    }
}