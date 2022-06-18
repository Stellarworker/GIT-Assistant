package com.stellarworker.gitassistant.ui.users

import androidx.lifecycle.LiveData

interface UsersContract {

    interface ViewModel {
        val usersLiveData: LiveData<MainActivityDataset>
        val errorLiveData: LiveData<Throwable>
        val progressLiveData: LiveData<Boolean>
        val openDetailsLiveData: LiveData<UserInfo>

        fun onRefresh()
        fun onUserClick(userInfo: UserInfo)
    }

}