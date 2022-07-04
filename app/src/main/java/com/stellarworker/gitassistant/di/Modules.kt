package com.stellarworker.gitassistant.di

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.data.retrofit.GithubApi
import com.stellarworker.gitassistant.data.retrofit.RetrofitUsersRepoImpl
import com.stellarworker.gitassistant.ui.users.UsersViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    single(qualifier("baseUrl")) { "https://api.github.com/" }
    single(qualifier("detailsData")) { "DETAILS_DATA" }
    single {
        Retrofit.Builder()
            .baseUrl(get<String>(named("baseUrl")))
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }
    single<GithubApi> {
        get<Retrofit>().create(GithubApi::class.java)
    }
    single<UsersRepo> {
        RetrofitUsersRepoImpl(get())
    }
    viewModel {
        UsersViewModel(get())
    }
}
