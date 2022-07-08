package com.stellarworker.gitassistant.di

import com.stellarworker.gitassistant.ui.userdetails.UserDetailsActivity
import com.stellarworker.gitassistant.ui.users.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun injectMainActivity(mainActivity: MainActivity)
    fun injectUserDetailsActivity(userDetailsActivity: UserDetailsActivity)
}