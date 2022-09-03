package com.abhi41.jetpackcodingwithcat.examples.Services


import android.app.*
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.abhi41.jetpackcodingwithcat.MainActivity
import com.abhi41.jetpackcodingwithcat.R


private const val TAG = "MyBackgroundService"
val CHANNEL_ID = "Channel_ID"

class MyForegroundService : Service() {


    @RequiresApi(Build.VERSION_CODES.O)
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

        createNotificationChannel()
        val notificationIntent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this,
            0, notificationIntent, 0)
        val notification: Notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Foreground Service")
            .setContentText("Foreground")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)

        return START_NOT_STICKY
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                CHANNEL_ID,
                "Foreground Service Channel",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            val manager = getSystemService(
                NotificationManager::class.java)
            manager.createNotificationChannel(serviceChannel)
        }
    }

}