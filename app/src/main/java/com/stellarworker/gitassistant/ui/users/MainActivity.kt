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

private const val DETAILS_DATA = "DETAILS_DATA"

class MainActivity : AppCompatActivity(), UsersContract.View {
    private lateinit var binding: ActivityMainBinding
    private val adapter = UsersAdapter(
        onItemClicked = { userInfo ->
            startActivity(Intent(this, UserDetailsActivity::class.java).apply {
                putExtra(DETAILS_DATA, userInfo)
            })
        }
    )
    private lateinit var presenter: UsersContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
        presenter = extractPresenter()
        presenter.attach(this)
    }

    private fun extractPresenter() =
        lastCustomNonConfigurationInstance as? UsersContract.Presenter
            ?: UsersPresenter(app.usersRepo)

    @Deprecated("Deprecated in Java")
    override fun onRetainCustomNonConfigurationInstance() = presenter

    private fun initViews() {
        binding.mainActivityRefreshButton.setOnClickListener {
            presenter.onRefresh()
        }
        initRecyclerView()
        showProgress(false)
        showContent(false)
    }

    override fun showUsers(dataset: MainActivityDataset) {
        adapter.setData(dataset)
    }

    override fun showError(error: Throwable) {
        binding.mainActivityRoot.makeSnackbar(
            text = error.message ?: getString(R.string.network_error)
        )
    }

    override fun showProgress(show: Boolean) {
        binding.mainActivityProgressBar.progressBarLayoutRoot.isVisible = show
    }

    override fun showContent(show: Boolean) {
        binding.mainActivityRecyclerView.isVisible = show
    }

    private fun initRecyclerView() {
        with(binding) {
            mainActivityRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            mainActivityRecyclerView.adapter = adapter
        }
    }

    override fun onDestroy() {
        presenter.detach()
        super.onDestroy()
    }

}