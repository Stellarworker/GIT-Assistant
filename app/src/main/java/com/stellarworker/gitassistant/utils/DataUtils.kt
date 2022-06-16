package com.stellarworker.gitassistant.utils

import com.stellarworker.gitassistant.data.entities.UserEntityGTO
import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import com.stellarworker.gitassistant.ui.users.MainActivityDataset
import com.stellarworker.gitassistant.ui.users.UserInfo

private const val EMPTY_STRING = ""

fun convertUserEntityGTOToUserInfo(userEntityGTO: UserEntityGTO) =
    with(userEntityGTO) {
        UserInfo(
            login = login ?: EMPTY_STRING,
            id = id?.toString() ?: EMPTY_STRING,
            type = type ?: EMPTY_STRING,
            avatarUrl = avatarUrl ?: EMPTY_STRING
        )
    }

fun makeMainActivityDataset(usersEntityGTO: UsersEntityGTO) =
    MainActivityDataset(
        users = usersEntityGTO.map { item -> convertUserEntityGTOToUserInfo(item) }
    )