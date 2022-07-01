package com.stellarworker.gitassistant.ui.userdetails

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import coil.load
import com.stellarworker.gitassistant.R
import com.stellarworker.gitassistant.databinding.ActivityUserDetailsBinding
import com.stellarworker.gitassistant.ui.users.UserInfo

private const val DETAILS_DATA = "DETAILS_DATA"

class UserDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val userInfo = intent.extras?.get(DETAILS_DATA) as? UserInfo
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
