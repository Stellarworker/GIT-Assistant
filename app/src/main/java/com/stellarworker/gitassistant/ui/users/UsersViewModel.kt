package com.stellarworker.gitassistant.ui.users

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.utils.SingleEventLiveData
import com.stellarworker.gitassistant.utils.makeMainActivityDataset

private const val ERROR_MESSAGE = "It is not MutableLiveData!"

class UsersViewModel(private val usersRepo: UsersRepo) : UsersContract.ViewModel {

    override val usersLiveData: LiveData<MainActivityDataset> = MutableLiveData()
    override val errorLiveData: LiveData<Throwable> = SingleEventLiveData()
    override val progressLiveData: LiveData<Boolean> = MutableLiveData()
    override val openDetailsLiveData: LiveData<UserInfo> = SingleEventLiveData()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userInfo: UserInfo) {
        openDetailsLiveData.mutable().postValue(userInfo)
    }

    private fun loadData() {
        progressLiveData.mutable().postValue(true)
        usersRepo.getUsers(
            onSuccess = {
                progressLiveData.mutable().postValue(false)
                usersLiveData.mutable().postValue(makeMainActivityDataset(it))
            },
            onError = {
                progressLiveData.mutable().postValue(false)
                errorLiveData.mutable().postValue(it)
            }
        )
    }

    private fun <T> LiveData<T>.mutable(): MutableLiveData<T> {
        return this as? MutableLiveData<T>
            ?: throw IllegalStateException(ERROR_MESSAGE)
    }
}