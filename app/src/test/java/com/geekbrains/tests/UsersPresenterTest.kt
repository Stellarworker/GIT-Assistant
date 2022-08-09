package com.geekbrains.tests

import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.ui.users.UsersContract
import com.stellarworker.gitassistant.ui.users.UsersPresenter
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class UsersPresenterTest {
    private lateinit var usersPresenter: UsersContract.Presenter

    @Mock
    private lateinit var usersRepo: UsersRepo

    @Mock
    private lateinit var view: UsersContract.View

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        usersPresenter = UsersPresenter(usersRepo)
        usersPresenter.attach(view)
    }

    @Test
    fun showProgress_Test() {
        usersPresenter.onRefresh()
        verify(view, atLeastOnce()).showProgress(anyBoolean())
    }

    @Test
    fun showContent_Test() {
        usersPresenter.onRefresh()
        verify(view, atLeastOnce()).showContent(anyBoolean())
    }

}