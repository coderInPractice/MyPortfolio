package com.example.stopwatch.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class StopwatchService : Service() {

    private val mLocalStopwatchBinder = StopwatchBinder()







    inner class StopwatchBinder : Binder() {
        fun getService(): StopwatchService = this@StopwatchService
    }

    override fun onBind(intent: Intent?): IBinder {
        return mLocalStopwatchBinder
    }
}