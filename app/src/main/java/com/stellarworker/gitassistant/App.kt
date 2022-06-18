package com.stellarworker.gitassistant

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.stellarworker.gitassistant.data.entities.RetrofitUsersRepoImpl
import com.stellarworker.gitassistant.data.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { RetrofitUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App