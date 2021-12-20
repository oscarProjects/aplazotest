package com.oscarvj.aplazotest.ui.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.core.content.ContextCompat
import com.oscarvj.aplazotest.R
import com.oscarvj.aplazotest.ui.view.main.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private var splashTimer: CountDownTimer? = null
    private val minute = 4L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        window.statusBarColor = ContextCompat.getColor(this, R.color.white)
        splashTimer = object : CountDownTimer(minute * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                Log.d(this::class.java.name, "millisUntilFinished $millisUntilFinished")
            }
            override fun onFinish() {
                navigate()
            }
        }.start()
    }

    private fun navigate(){
        val intent = Intent(this, MainActivity::class.java)
        intent.flags =  Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
        finish()
    }
}