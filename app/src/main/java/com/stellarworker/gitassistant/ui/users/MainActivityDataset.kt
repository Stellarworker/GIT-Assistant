package com.stellarworker.gitassistant.ui.users

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

private const val EMPTY_STRING = ""

@Parcelize
data class UserInfo(
    val login: String = EMPTY_STRING,
    val id: String = EMPTY_STRING,
    val type: String = EMPTY_STRING,
    val avatarUrl: String = EMPTY_STRING
) : Parcelable

data class MainActivityDataset(
    val users: List<UserInfo> = listOf()
)