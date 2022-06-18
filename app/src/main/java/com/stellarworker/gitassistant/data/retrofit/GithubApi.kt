package com.stellarworker.gitassistant.data.retrofit

import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import retrofit2.Call
import retrofit2.http.GET

private const val GITHUB_USERS_URL = "users"

interface GithubApi {
    @GET(GITHUB_USERS_URL)
    fun getUsers(): Call<UsersEntityGTO>
}