package com.example.musicsampleapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MusicApplication : Application()  {

    override fun onCreate() {
        super.onCreate()

    }

}