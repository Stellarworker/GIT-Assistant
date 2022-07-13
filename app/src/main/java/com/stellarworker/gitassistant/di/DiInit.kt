package com.stellarworker.gitassistant.di

import com.stellarworker.gitassistant.data.retrofit.GithubApi
import com.stellarworker.gitassistant.data.retrofit.RetrofitUsersRepoImpl
import com.stellarworker.gitassistant.domain.repos.UsersRepo
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class DiInit {

    private val baseUrl = "https://api.github.com/"
    private val detailsData = "DETAILS DATA"
    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
    private val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }

    init {
        DiKeeper.add(UsersRepo::class, Singleton { usersRepo })
        DiKeeper.add(String::class, Singleton { detailsData })
    }
}