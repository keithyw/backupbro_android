package com.backupbro

import android.app.Application
import com.backupbro.di.backupBroApp
import org.koin.android.ext.android.startKoin

class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin(this, backupBroApp)
    }
}