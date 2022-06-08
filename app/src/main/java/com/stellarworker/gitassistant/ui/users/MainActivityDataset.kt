package com.stellarworker.gitassistant.ui.users

private const val EMPTY_STRING = ""
private const val EMPTY_LONG = -1L

data class UserInfo(
    val login: String = EMPTY_STRING,
    val id: Long = EMPTY_LONG,
    val avatarUrl: String = EMPTY_STRING
)

data class MainActivityDataset(
    val users: List<UserInfo> = listOf()
)