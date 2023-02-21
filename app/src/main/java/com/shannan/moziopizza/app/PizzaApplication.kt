package com.shannan.moziopizza.app

import android.app.Application
import com.shannan.moziopizza.BuildConfig
import com.shannan.moziopizza.appModule
import com.shannan.moziopizza.base.baseModule
import com.shannan.moziopizza.feature.flavors.featureAlbumModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import timber.log.Timber

class PizzaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
        initTimber()
    }

    private fun initKoin() {
        GlobalContext.startKoin {
            androidLogger()
            androidContext(this@PizzaApplication)

            modules(appModule)
            modules(baseModule)
            modules(featureAlbumModules)
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
