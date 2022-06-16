package com.stellarworker.gitassistant.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.databinding.UsersRecyclerviewItemBinding

class UserViewHolder(
    parent: ViewGroup,
    private val onItemClicked: ((userInfo: UserInfo) -> Unit)? = null
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.users_recyclerview_item, parent, false)
) {
    private val binding = UsersRecyclerviewItemBinding.bind(itemView)

    fun bind(userInfo: UserInfo) {
        with(binding) {
            usersRecyclerviewItemAvatar.load(userInfo.avatarUrl)
            usersRecyclerviewItemLogin.text = userInfo.login
            usersRecyclerviewItemUid.text = userInfo.id
        }
        itemView.setOnClickListener {
            onItemClicked?.invoke(userInfo)
        }
    }
}