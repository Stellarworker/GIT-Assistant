package com.stellarworker.gitassistant.ui.users

import com.stellarworker.gitassistant.data.entities.UserEntityGTO
import com.stellarworker.gitassistant.data.entities.UsersEntityGTO
import com.stellarworker.gitassistant.data.repos.UsersRepo
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import io.reactivex.rxjava3.subjects.Subject

private const val ERROR_MESSAGE = "It is not MutableLiveData!"
private const val EMPTY_STRING = ""

class UsersViewModel(private val usersRepo: UsersRepo) : UsersContract.ViewModel {

    override val usersLiveData: Observable<MainActivityDataset> = BehaviorSubject.create()
    override val errorLiveData: Observable<Throwable> = BehaviorSubject.create()
    override val progressLiveData: Observable<Boolean> = BehaviorSubject.create()
    override val openDetailsLiveData: Observable<UserInfo> = BehaviorSubject.create()

    override fun onRefresh() {
        loadData()
    }

    override fun onUserClick(userInfo: UserInfo) {
        openDetailsLiveData.mutable().onNext(userInfo)
    }

    private fun loadData() {
        progressLiveData.mutable().onNext(true)
        usersRepo.getUsers()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSuccess {
                progressLiveData.mutable().onNext(false)
                usersLiveData.mutable().onNext(makeMainActivityDataset(it))
            }
            .doOnError {
                progressLiveData.mutable().onNext(false)
                errorLiveData.mutable().onNext(it)
            }
            .subscribe()
    }

    private fun <T : Any> Observable<T>.mutable(): Subject<T> {
        return this as? Subject<T>
            ?: throw IllegalStateException(ERROR_MESSAGE)
    }

    private fun convertUserEntityGTOToUserInfo(userEntityGTO: UserEntityGTO) =
        with(userEntityGTO) {
            UserInfo(
                login = login ?: EMPTY_STRING,
                id = id?.toString() ?: EMPTY_STRING,
                type = type ?: EMPTY_STRING,
                avatarUrl = avatarUrl ?: EMPTY_STRING
            )
        }

    private fun makeMainActivityDataset(usersEntityGTO: UsersEntityGTO) =
        MainActivityDataset(
            users = usersEntityGTO.map { item -> convertUserEntityGTOToUserInfo(item) }
        )
}