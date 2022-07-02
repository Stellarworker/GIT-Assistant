package com.stellarworker.gitassistant.data.retrofit

import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import com.stellarworker.gitassistant.data.repos.UsersRepo
import io.reactivex.rxjava3.core.Single

class RetrofitUsersRepoImpl(private val api: GithubApi) : UsersRepo {
    override fun getUsers(): Single<UsersEntityGTO> = api.getUsers()
}