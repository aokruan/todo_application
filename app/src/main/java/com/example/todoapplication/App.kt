package com.example.todoapplication

import android.app.Application
import com.example.todoapplication.di.*
import com.example.todoapplication.domain.service.api.ApiHolder
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

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