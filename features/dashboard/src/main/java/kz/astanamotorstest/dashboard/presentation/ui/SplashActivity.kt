package kz.astanamotorstest.dashboard.presentation.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kz.astanamotorstest.dashboard.presentation.ui.dashboard.DashboardContainer
import timber.log.Timber

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
        startActivity(DashboardContainer.newIntent(this))
        finish()
    }
}