package com.stellarworker.gitassistant

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.data.retrofit.GithubApi
import com.stellarworker.gitassistant.data.retrofit.RetrofitUsersRepoImpl
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    private val githubBaseUrl = "https://api.github.com/"
    private val retrofit = Retrofit.Builder()
        .baseUrl(githubBaseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
    private val api: GithubApi by lazy { retrofit.create(GithubApi::class.java) }
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl(api) }
    val detailsData = "DETAILS_DATA"
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App