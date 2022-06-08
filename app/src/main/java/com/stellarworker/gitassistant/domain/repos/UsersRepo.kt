package com.stellarworker.gitassistant.domain.repos

import com.stellarworker.gitassistant.domain.entities.UsersEntityGTO

interface UsersRepo {
    fun getUsers(
        onSuccess: (UsersEntityGTO) -> Unit,
        onError: ((Throwable) -> Unit)? = null
    )
}