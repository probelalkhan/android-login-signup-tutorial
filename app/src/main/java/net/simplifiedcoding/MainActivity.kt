package net.simplifiedcoding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.asLiveData
import dagger.hilt.android.AndroidEntryPoint
import net.simplifiedcoding.data.UserPreferences
import net.simplifiedcoding.ui.auth.AuthActivity
import net.simplifiedcoding.ui.home.HomeActivity
import net.simplifiedcoding.ui.startNewActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val userPreferences = UserPreferences(this)

        userPreferences.accessToken.asLiveData().observe(this, Observer {
            val activity = if (it == null) AuthActivity::class.java else HomeActivity::class.java
            startNewActivity(activity)
        })
    }

}