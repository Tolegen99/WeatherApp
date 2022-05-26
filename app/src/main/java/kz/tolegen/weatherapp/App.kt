package kz.tolegen.weatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
    }


    companion object {
        internal lateinit var INSTANCE: App
            private set
    }
}