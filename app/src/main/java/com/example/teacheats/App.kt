package com.example.teacheats

import android.app.Application
import timber.log.Timber

class App : Application() {
    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
    companion object {
        const val PREFS_NAME = "NotKotDogPrefs"
        const val AUTH_TOKEN_KEY = "AuthToken"
    }
}