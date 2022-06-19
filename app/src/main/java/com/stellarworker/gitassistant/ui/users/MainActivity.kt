package com.stellarworker.gitassistant.ui.users

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.app
import com.stellarworker.gitassistant.databinding.ActivityMainBinding
import com.stellarworker.gitassistant.ui.users.userdetails.UserDetailsActivity
import com.stellarworker.gitassistant.utils.makeSnackbar
import io.reactivex.rxjava3.disposables.CompositeDisposable

private const val DETAILS_DATA = "DETAILS_DATA"

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter {
        viewModel.onUserClick(it)
    }
    private lateinit var viewModel: UsersContract.ViewModel
    private val viewModelDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel = extractViewModel()
        with(viewModel) {
            viewModelDisposable.addAll(
                progressLiveData.subscribe {
                    showProgress(it)
                    showContent(!it)
                },
                usersLiveData.subscribe {
                    showUsers(it)
                    showContent(true)
                },
                errorLiveData.subscribe {
                    showError(it)
                },
                openDetailsLiveData.subscribe {
                    openDetailsScreen(it)
                }
            )
        }
    }

    private fun openDetailsScreen(userInfo: UserInfo) {
        startActivity(Intent(this, UserDetailsActivity::class.java).apply {
            putExtra(DETAILS_DATA, userInfo)
        })
    }

    private fun extractViewModel() =
        lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(app.usersRepo)

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance() = viewModel

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            viewModel.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
        showContent(false)
    }

    private fun showUsers(dataset: MainActivityDataset) {
        adapter.setData(dataset)
    }

    private fun showError(error: Throwable) {
        binding.mainActivityRoot.makeSnackbar(
            text = error.message ?: getString(R.string.network_error)
        )
    }

    private fun showProgress(show: Boolean) {
        binding.mainActivityProgressBar.progressBarLayoutRoot.isVisible = show
    }

    private fun showContent(show: Boolean) {
        binding.mainActivityRecyclerView.isVisible = show
    }

    private fun initRecyclerView() {
        with(binding) {
            mainActivityRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            mainActivityRecyclerView.adapter = adapter
        }
    }

    override fun onDestroy() {
        viewModelDisposable.dispose()
        super.onDestroy()
    }
}