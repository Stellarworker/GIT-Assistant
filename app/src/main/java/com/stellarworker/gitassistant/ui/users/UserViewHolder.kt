package com.stellarworker.gitassistant.ui.users

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.databinding.ActivityMainRecyclerviewItemBinding

class UserViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
        .inflate(R.layout.activity_main_recyclerview_item, parent, false)
) {
    private val binding = ActivityMainRecyclerviewItemBinding.bind(itemView)

    fun bind(userInfo: UserInfo) {
        with(binding) {
            activityMainRecyclerviewItemAvatar.load(userInfo.avatarUrl)
            activityMainRecyclerviewItemLogin.text = userInfo.login
            activityMainRecyclerviewItemUid.text = userInfo.id.toString()
        }
    }
}