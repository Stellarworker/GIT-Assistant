package com.stellarworker.gitassistant.ui.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.app
import com.stellarworker.gitassistant.databinding.ActivityUserDetailsBinding
import com.stellarworker.gitassistant.ui.users.UserInfo
import javax.inject.Inject

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    @Inject
    lateinit var detailsData: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        app.appComponent.injectUserDetailsActivity(this)
        val userInfo = intent.extras?.get(detailsData) as? UserInfo
        userInfo?.let { info ->
            showUserInfo(info)
        }
    }

    private fun showUserInfo(userInfo: UserInfo) {
        with(binding) {
            userDetailsActivityAvatar.load(userInfo.avatarUrl)
            userDetailsActivityUid.text =
                String.format(getString(R.string.user_details_activity_uid), userInfo.id)
            userDetailsActivityLogin.text =
                String.format(getString(R.string.user_details_activity_login), userInfo.login)
            userDetailsActivityType.text =
                String.format(getString(R.string.user_details_activity_type), userInfo.type)
        }
    }
}
