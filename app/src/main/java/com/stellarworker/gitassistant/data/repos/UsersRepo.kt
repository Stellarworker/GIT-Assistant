package com.stellarworker.gitassistant.data.repos

import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import io.reactivex.rxjava3.core.Single

interface UsersRepo {
    fun getUsers(): Single<UsersEntityGTO>
}