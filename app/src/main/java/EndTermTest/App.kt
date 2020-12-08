package EndTermTest

import android.app.Application
import android.content.Context
import kz.astanamotorstest.dashboard.presentation.di.movieNetworkModule
import kz.astanamotorstest.dashboard.presentation.di.movieRepositoryModule
import kz.astanamotorstest.dashboard.presentation.di.movieUseCaseModule
import kz.astanamotorstest.dashboard.presentation.di.movieViewModelModule
import kz.astanamotorstest.di.networkModule
import kz.astanamotorstest.movieitem.presentation.di.*
import kz.astanamotorstest.ui_components.utils.BuildType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var appContext: Context
    }

    private val movieModules = listOf(
        movieNetworkModule,
        movieRepositoryModule,
        movieViewModelModule,
        movieUseCaseModule
    )

    private val itemModules = listOf(
        itemNetworkModule,
        itemRepositoryModule,
        itemViewModelModule,
        itemUseCaseModule,
        itemLocalModule
    )

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        Timber.i("onCreate")
        appContext = applicationContext

        startKoin {
            fileProperties()
            androidLogger()
            androidContext(appContext)
            modules(networkModule)
            modules(movieModules)
            modules(itemModules)
        }
    }

    private fun setupTimber() {
        if (BuildType.isDebug()) Timber.plant(Timber.DebugTree())
    }
}