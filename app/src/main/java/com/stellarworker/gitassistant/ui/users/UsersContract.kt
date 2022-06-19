package com.stellarworker.gitassistant.ui.users

import io.reactivex.rxjava3.core.Observable

interface UsersContract {

    interface ViewModel {
        val usersLiveData: Observable<MainActivityDataset>
        val errorLiveData: Observable<Throwable>
        val progressLiveData: Observable<Boolean>
        val openDetailsLiveData: Observable<UserInfo>

        fun onRefresh()
        fun onUserClick(userInfo: UserInfo)
    }

}