package com.stellarworker.gitassistant.domain.repos

import com.stellarworker.gitassistant.data.retrofit.UsersEntityDTO
import io.reactivex.rxjava3.core.Single

interface UsersRepo {
    fun getUsers(): Single<UsersEntityDTO>
}