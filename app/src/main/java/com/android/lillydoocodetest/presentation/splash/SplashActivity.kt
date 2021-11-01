package com.android.lillydoocodetest.presentation.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.android.lillydoocodetest.R
import com.android.lillydoocodetest.databinding.ActivitySplashBinding
import com.android.lillydoocodetest.presentation.jokes.JokesListActivity
import com.android.lillydoocodetest.util.IPreferenceHelper
import com.android.lillydoocodetest.util.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_splash.*

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var databinding: ActivitySplashBinding
    private val handler = Handler(Looper.getMainLooper())
    private val preferenceHelper: IPreferenceHelper by lazy { PreferenceManager(applicationContext) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        databinding = DataBindingUtil.setContentView(this,R.layout.activity_splash)
        databinding.splashViewModel=viewModel
        databinding.lifecycleOwner = this
        supportActionBar?.setDisplayShowTitleEnabled(false)

        var totalCount=preferenceHelper.getAppOpenedCountKey()
        totalCount++
        preferenceHelper.setAppOpenedCountKey(totalCount)

        viewModel.updateAppOpenedCount(totalCount)
        nextScreen()
    }
    private fun nextScreen() {
        handler.postDelayed({
            startActivity(Intent(this@SplashActivity, JokesListActivity::class.java))
            finish()
        }, 3000)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacksAndMessages(null)
        finish()
    }
}