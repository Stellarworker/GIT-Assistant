package com.stellarworker.gitassistant.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.stellarworker.gitassistant.data.entities.UserEntityGTO
import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import com.stellarworker.gitassistant.ui.users.MainActivityDataset
import com.stellarworker.gitassistant.ui.users.UserInfo
import java.util.concurrent.atomic.AtomicBoolean

private const val EMPTY_STRING = ""
private const val MULTIPLE_OBSERVERS_WARNING_MESSAGE =
    "Multiple observers registered but only one will be notified of changes."

fun convertUserEntityGTOToUserInfo(userEntityGTO: UserEntityGTO) =
    with(userEntityGTO) {
        UserInfo(
            login = login ?: EMPTY_STRING,
            id = id?.toString() ?: EMPTY_STRING,
            type = type ?: EMPTY_STRING,
            avatarUrl = avatarUrl ?: EMPTY_STRING
        )
    }

fun makeMainActivityDataset(usersEntityGTO: UsersEntityGTO) =
    MainActivityDataset(
        users = usersEntityGTO.map { item -> convertUserEntityGTOToUserInfo(item) }
    )

class SingleEventLiveData<T> : MutableLiveData<T>() {
    private val pending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, MULTIPLE_OBSERVERS_WARNING_MESSAGE)
        }
        super.observe(owner, Observer { t ->
            if (pending.compareAndSet(true, false)) {
                observer.onChanged(t)
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        pending.set(true)
        super.setValue(t)
    }

    companion object {
        private const val TAG = "SingleLiveEvent"
    }
}