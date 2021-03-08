package com.munki.paging

import android.app.Application
import androidx.multidex.MultiDexApplication
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

/**
 * Activity에서 공통적으로 적용되는 상위 MultiDexApplication
 * @author 나비이쁜이
 * @since 2021.03.09
 */
class GlobalApplication : MultiDexApplication() {

    /**
     * onCreate
     */
    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    /**
     * initDI
     */
    private fun Application.initDI() {
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@initDI)
            modules(com.munki.paging.di.modules)
        }
    }
}