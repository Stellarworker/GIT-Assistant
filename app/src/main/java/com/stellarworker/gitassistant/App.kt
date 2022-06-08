package com.stellarworker.gitassistant

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.stellarworker.gitassistant.data.TestUsersRepoImpl
import com.stellarworker.gitassistant.domain.repos.UsersRepo

class App : Application() {
    val usersRepo: UsersRepo by lazy { TestUsersRepoImpl() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App