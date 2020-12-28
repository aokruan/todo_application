package com.example.todoapplication.presentation.ui.main

import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.todoapplication.R
import com.google.android.gms.common.GooglePlayServicesNotAvailableException
import com.google.android.gms.common.GooglePlayServicesRepairableException
import com.google.android.gms.common.GooglePlayServicesUtil
import com.google.android.gms.security.ProviderInstaller

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        updateAndroidSecurityProvider(this)
    }

    /* Метод для возможности запуска приложений на android 4.4.2
    *
    * App():MultiDexApplication()
    *
    * build.gradle(app)
    *
    * implementation 'com.android.support:multidex:1.0.3'
    * implementation 'com.google.android.gms:play-services-auth:16.0.1'
    *
    * compileOptions {
    *   sourceCompatibility JavaVersion.VERSION_1_8
    *   targetCompatibility JavaVersion.VERSION_1_8
    * }
    *
    * multiDexEnabled true
    * */
    private fun updateAndroidSecurityProvider(callingActivity: Activity) {
        try {
            ProviderInstaller.installIfNeeded(this)
        } catch (e: GooglePlayServicesRepairableException) {
            GooglePlayServicesUtil.getErrorDialog(e.connectionStatusCode, callingActivity, 0)
        } catch (e: GooglePlayServicesNotAvailableException) {
            Log.e("SecurityException", "Google Play Services not available.")
        }
    }
}