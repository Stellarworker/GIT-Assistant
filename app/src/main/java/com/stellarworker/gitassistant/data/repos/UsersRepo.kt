package com.stellarworker.gitassistant.data.repos

import com.stellarworker.gitassistant.data.entities.UsersEntityGTO

interface UsersRepo {
    fun getUsers(
        onSuccess: (UsersEntityGTO) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}