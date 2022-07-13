package com.stellarworker.gitassistant

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.stellarworker.gitassistant.di.DiInit

class App : Application() {
    init {
        DiInit()
    }
}

val Context.app: App get() = applicationContext as App
val Fragment.app: App get() = requireContext().applicationContext as App