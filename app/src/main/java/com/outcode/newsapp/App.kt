package com.outcode.newsapp

import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber


/**
 * Created by Ayush Shrestha$ on 2022/11/17$.
 */
@HiltAndroidApp
class App:Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        Timber.plant(Timber.DebugTree())
    }


    companion object {
        lateinit var instance: App

        fun isNetworkConnected(): Boolean {
            val connectivityManager =
                instance.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}