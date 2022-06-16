package com.stellarworker.gitassistant.ui.users

interface UsersContract {

    interface View {
        fun showUsers(dataset: MainActivityDataset)
        fun showError(error: Throwable)
        fun showProgress(show: Boolean)
        fun showContent(show: Boolean)
    }

    interface Presenter {
        fun attach(view: View)
        fun detach()
        fun onRefresh()
    }

}