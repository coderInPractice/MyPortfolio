package com.example.musicsampleapp.service

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder

class MusicBoundService : Service () {

    private val mLocalMusicBinder = MusicBinder()



    inner class MusicBinder : Binder() {
        fun getService(): MusicBoundService {
            return this@MusicBoundService
        }
    }
    override fun onBind(intent: Intent?): IBinder {
        return mLocalMusicBinder
    }
}