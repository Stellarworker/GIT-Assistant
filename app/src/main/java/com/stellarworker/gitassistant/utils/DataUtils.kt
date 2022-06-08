package com.stellarworker.gitassistant.utils

import com.stellarworker.gitassistant.domain.entities.UserEntityGTO
import com.stellarworker.gitassistant.domain.entities.UsersEntityGTO
import com.stellarworker.gitassistant.ui.users.MainActivityDataset
import com.stellarworker.gitassistant.ui.users.UserInfo

private const val EMPTY_STRING = ""
private const val EMPTY_LONG = -1L

fun convertUserEntityGTOToUserInfo(userEntityGTO: UserEntityGTO) =
    with(userEntityGTO) {
        UserInfo(
            login = login ?: EMPTY_STRING,
            id = id ?: EMPTY_LONG,
            avatarUrl = avatarUrl ?: EMPTY_STRING
        )
    }

fun makeMainActivityDataset(usersEntityGTO: UsersEntityGTO) =
    MainActivityDataset(
        users = usersEntityGTO.map { item -> convertUserEntityGTOToUserInfo(item) }
    )