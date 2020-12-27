package com.example.todoapplication

import androidx.multidex.MultiDexApplication
import com.example.todoapplication.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            androidLogger(Level.DEBUG)
            modules(
                listOf(
                    viewModelModule,
                    repositoryModule,
                    databaseModule,
                    apiModule,
                    netModule,
                    serviceModule
                )
            )
        }
    }
}