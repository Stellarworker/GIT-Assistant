package com.stellarworker.gitassistant.ui.users

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.utils.makeMainActivityDataset

class UsersPresenter(private val usersRepo: UsersRepo) : UsersContract.Presenter {
    private var view: UsersContract.View? = null
    private var usersList: MainActivityDataset? = null
    private var inProgress: Boolean = false

    override fun attach(view: UsersContract.View) {
        this.view = view
        view.showProgress(inProgress)
        view.showContent(!inProgress)
        usersList?.let { view.showUsers(it) }
    }

    override fun detach() {
        view = null
    }

    override fun onRefresh() {
        view?.let {
            it.showProgress(true)
            it.showContent(false)
        }
        inProgress = true
        usersRepo.getUsers(
            onSuccess = { gto ->
                usersList = makeMainActivityDataset(gto)
                view?.let {
                    it.showProgress(false)
                    it.showContent(true)
                    it.showUsers(makeMainActivityDataset(gto))
                }
                inProgress = false
            },
            onError = { error ->
                view?.let {
                    it.showProgress(false)
                    it.showContent(false)
                    it.showError(error)
                }
                inProgress = false
            }
        )
    }
}
