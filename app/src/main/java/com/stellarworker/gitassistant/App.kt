package com.stellarworker.gitassistant

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.stellarworker.gitassistant.di.AppComponent
import com.stellarworker.gitassistant.di.DaggerAppComponent

class App : Application() {
    val appComponent: AppComponent by lazy { DaggerAppComponent.create() }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App