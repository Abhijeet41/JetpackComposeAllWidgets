package com.abhi41.jetpackcodingwithcat.examples.Services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

private const val TAG = "MyBackgroundService"

class MyBackgroundService : Service() {

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(
            Runnable {
                run {
                    while (true) {
                        Log.e(TAG, "Service is running...")
                        try {
                            Thread.sleep(2000)
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    }
                }
            }
        ).start()

        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}