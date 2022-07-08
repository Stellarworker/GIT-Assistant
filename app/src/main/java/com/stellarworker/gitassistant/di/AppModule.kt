package com.stellarworker.gitassistant.di

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.data.retrofit.GithubApi
import com.stellarworker.gitassistant.data.retrofit.RetrofitUsersRepoImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    private val baseUrl = "https://api.github.com/"

    @Provides
    @Singleton
    fun provideDetailsData() = "DETAILS_DATA"

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideGithubApi(retrofit: Retrofit): GithubApi {
        return retrofit.create(GithubApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUsersRepo(api: GithubApi): UsersRepo {
        return RetrofitUsersRepoImpl(api)
    }
}