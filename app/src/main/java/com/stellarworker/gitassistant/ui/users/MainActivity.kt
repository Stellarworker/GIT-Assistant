package com.stellarworker.gitassistant.ui.users

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.app
import com.stellarworker.gitassistant.databinding.ActivityMainBinding
import com.stellarworker.gitassistant.domain.entities.UsersEntityGTO
import com.stellarworker.gitassistant.domain.repos.UsersRepo
import com.stellarworker.gitassistant.utils.makeMainActivityDataset
import com.stellarworker.gitassistant.utils.makeSnackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter()
    private val usersRepo: UsersRepo by lazy { app.usersRepo }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            adapter.clearData()
            loadData()
        }
        initRecyclerView()
        showProgress(false)
        showContent(false)
    }

    private fun loadData() {
        showProgress(true)
        showContent(false)
        usersRepo.getUsers(
            onSuccess = { gto ->
                onDataLoaded(gto)
                showProgress(false)
                showContent(true)
            },
            onError = { error ->
                onError(error)
                showProgress(false)
                showContent(false)
            }
        )
    }

    private fun onDataLoaded(data: UsersEntityGTO) {
        adapter.setData(makeMainActivityDataset(data))
    }

    private fun onError(throwable: Throwable) {
        binding.mainActivityRoot.makeSnackbar(
            text = throwable.message ?: getString(R.string.network_error)
        )
    }


    private fun initRecyclerView() {
        with(binding) {
            mainActivityRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            mainActivityRecyclerView.adapter = adapter
        }
    }

    private fun showProgress(show: Boolean) {
        binding.mainActivityProgressBar.progressBarLayoutRoot.isVisible = show
    }

    private fun showContent(show: Boolean) {
        binding.mainActivityRecyclerView.isVisible = show
    }

}