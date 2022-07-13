package com.stellarworker.gitassistant.data.retrofit

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

private const val GITHUB_USERS_URL = "users"

interface GithubApi {
    @GET(GITHUB_USERS_URL)
    fun getUsers(): Single<UsersEntityDTO>
}