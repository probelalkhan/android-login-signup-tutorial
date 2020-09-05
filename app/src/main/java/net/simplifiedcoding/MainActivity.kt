package net.simplifiedcoding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.simplifiedcoding.auth.AuthActivity

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        finish()
        startActivity(Intent(this, AuthActivity::class.java))
    }

}