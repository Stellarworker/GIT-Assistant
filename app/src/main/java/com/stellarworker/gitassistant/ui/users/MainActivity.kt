package com.stellarworker.gitassistant.ui.users

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.app
import com.stellarworker.gitassistant.data.repos.UsersRepo
import com.stellarworker.gitassistant.databinding.ActivityMainBinding
import com.stellarworker.gitassistant.ui.userdetails.UserDetailsActivity
import io.reactivex.rxjava3.disposables.CompositeDisposable

private const val EMPTY_STRING = ""

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter { userInfo ->
        viewModel.onUserClick(userInfo)
    }
    private lateinit var viewModel: UsersContract.ViewModel
    private val usersRepo: UsersRepo by lazy { app.usersRepo }
    private val viewModelDisposable = CompositeDisposable()
    private val refreshButtonDisposable = CompositeDisposable()

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
            putExtra(app.detailsData, userInfo)
        })
    }

    private fun extractViewModel() =
        lastCustomNonConfigurationInstance as? UsersContract.ViewModel
            ?: UsersViewModel(usersRepo)

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance() = viewModel

    private fun initViews() {
        refreshButtonDisposable.add(
            binding.mainActivityRefreshButton.clickEvent.subscribe {
                viewModel.onRefresh()
            })
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
        refreshButtonDisposable.dispose()
        super.onDestroy()
    }

    private fun View.makeSnackbar(
        text: String = EMPTY_STRING,
        actionText: String = EMPTY_STRING,
        action: (View) -> Unit = {},
        length: Int = Snackbar.LENGTH_LONG,
        anchor: View? = null
    ) {
        Snackbar
            .make(this, text, length)
            .setAction(actionText, action)
            .setAnchorView(anchor)
            .show()
    }
}