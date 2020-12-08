package kz.astanamotorstest.dashboard.presentation.ui.dashboard

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import kz.astanamotorstest.dashboard.databinding.ContainerDashboardBinding
import timber.log.Timber

class DashboardContainer : AppCompatActivity() {

    companion object {
        fun newIntent(context: Context?) = Intent(context, DashboardContainer::class.java)
    }

    private lateinit var binding: ContainerDashboardBinding

    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.i("onCreate")
        binding = ContainerDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        if (savedInstanceState == null) {
//            initBottomNavigationBar()
//        }
    }

//    private fun initBottomNavigationBar() {
//        Timber.i("initBottomNavigationBar")
//        val navGraphIds = listOf(
//            R.navigation.home_nav,
//            R.navigation.favorite_nav
//        )

//        val controller = binding.bottomNavigation.setupWithNavController(
//            navGraphIds = navGraphIds,
//            fragmentManager = supportFragmentManager,
//            containerId = R.id.nav_host_container,
//            intent = intent
//        )

//        currentNavController = controller
//
//        controller.observe(this, Observer(::onControllerChanged))
}

//    private fun onControllerChanged(navController: NavController) {
//        Timber.i("onControllerChanged, navController: $navController")
//        navController.addOnDestinationChangedListener { _, destination, _ ->
//            Timber.i("addOnDestinationChangedListener ${destination.id}")
//            when (destination.id) {
//                R.id.item_fragment -> binding.bottomNavigation.visibility = View.GONE
//                else -> {
//                    binding.bottomNavigation.visibility = View.VISIBLE
//                }
//            }
//        }
//    }
//}