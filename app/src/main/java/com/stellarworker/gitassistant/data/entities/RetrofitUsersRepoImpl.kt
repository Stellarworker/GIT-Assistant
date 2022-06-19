package com.stellarworker.gitassistant.data.entities

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.data.retrofit.GithubApi
import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

private const val GITHUB_BASE_URL = "https://api.github.com/"

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    override fun getUsers(): Single<UsersEntityGTO> = api.getUsers()

}