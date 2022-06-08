package com.stellarworker.gitassistant.data

import android.os.Handler
import android.os.Looper
import com.stellarworker.gitassistant.domain.entities.UserEntityGTO
import com.stellarworker.gitassistant.domain.entities.UsersEntityGTO
import com.stellarworker.gitassistant.domain.repos.UsersRepo

private const val DATA_LOADING_DELAY = 3000L
private const val SUCCESS_LIKELIHOOD = 0.5
private const val NETWORK_ERROR_MESSAGE = "Network error!"

class TestUsersRepoImpl : UsersRepo {

    private val usersData: UsersEntityGTO = listOf(
        UserEntityGTO(
            login = "mojombo", id = 1,
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        UserEntityGTO(
            login = "defunkt", id = 2,
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4"
        ),
        UserEntityGTO(
            login = "pjhyett", id = 3,
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4"
        ),
        UserEntityGTO(
            login = "wycats", id = 4,
            avatarUrl = "https://avatars.githubusercontent.com/u/4?v=4"
        ),
        UserEntityGTO(
            login = "ezmobius", id = 5,
            avatarUrl = "https://avatars.githubusercontent.com/u/5?v=4"
        ),
        UserEntityGTO(
            login = "ivey", id = 6,
            avatarUrl = "https://avatars.githubusercontent.com/u/6?v=4"
        ),
        UserEntityGTO(
            login = "ivey", id = 7,
            avatarUrl = "https://avatars.githubusercontent.com/u/7?v=4"
        ),
        UserEntityGTO(
            login = "evanphx", id = 8,
            avatarUrl = "https://avatars.githubusercontent.com/u/8?v=4"
        ),
        UserEntityGTO(
            login = "vanpelt", id = 17,
            avatarUrl = "https://avatars.githubusercontent.com/u/17?v=4"
        ),
        UserEntityGTO(
            login = "wayneeseguin", id = 18,
            avatarUrl = "https://avatars.githubusercontent.com/u/18?v=4"
        )
    )

    override fun getUsers(
        onSuccess: (UsersEntityGTO) -> Unit,
        onError: ((Throwable) -> Unit)?
    ) {
        Handler(Looper.getMainLooper()).postDelayed({
            if (Math.random() > SUCCESS_LIKELIHOOD) {
                onSuccess(usersData)
            } else {
                onError?.invoke(Throwable(NETWORK_ERROR_MESSAGE))
            }
        }, DATA_LOADING_DELAY)
    }

}