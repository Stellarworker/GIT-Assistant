package com.stellarworker.gitassistant.data.entities

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.data.retrofit.GithubApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val GITHUB_BASE_URL = "https://api.github.com/"
private const val ERROR_MESSAGE = "No data or network error!"

class RetrofitUsersRepoImpl : UsersRepo {

    private val retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: GithubApi = retrofit.create(GithubApi::class.java)

    override fun getUsers(
        onSuccess: (UsersEntityGTO) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        api.getUsers().enqueue(object : Callback<UsersEntityGTO> {
            override fun onResponse(
                call: Call<UsersEntityGTO>,
                response: Response<UsersEntityGTO>
            ) {
                val body = response.body()
                if (response.isSuccessful && body != null) {
                    onSuccess.invoke(body)
                } else {
                    onError?.invoke(IllegalStateException(ERROR_MESSAGE))
                }
            }

            override fun onFailure(call: Call<UsersEntityGTO>, t: Throwable) {
                onError?.invoke(t)
            }

        })
    }
}