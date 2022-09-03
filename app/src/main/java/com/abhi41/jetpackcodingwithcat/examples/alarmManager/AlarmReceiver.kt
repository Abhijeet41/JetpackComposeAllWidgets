package com.abhi41.jetpackcodingwithcat.examples.alarmManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.abhi41.jetpackcodingwithcat.R
import com.abhi41.jetpackcodingwithcat.di.DefaultNotificationCompactBuilder
import com.abhi41.jetpackcodingwithcat.di.DownloadNotificationCompactBuilder
import com.abhi41.jetpackcodingwithcat.util.Constants
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.internal.notify
import javax.inject.Inject

@AndroidEntryPoint
class AlarmReceiver : BroadcastReceiver() {
    private val TAG = "Alarm"

    @DefaultNotificationCompactBuilder
    @Inject
    lateinit var defaultNotificationBuilder: NotificationCompat.Builder
    @Inject
    lateinit var notificationManager: NotificationManagerCompat


    override fun onReceive(context: Context, intent: Intent?) {


        try {
            // showNotification(context, "title", "description")
            notificationManager.notify(Constants.NOTIFICATION_UPDATED_ID,
                defaultNotificationBuilder
                    .setContentTitle("Alarm Manager demo")
                    .setContentText("so with the help of field injuction we achive notification in" +
                            "broadcast receiver")
                    .build()
            )
        } catch (e: Exception) {
            Log.d(TAG, "onReceive: ${e.printStackTrace().toString()}")
        }
    }

    private fun showNotification(context: Context, title: String, description: String) {
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val channelName = "message_channel"
        val channelId = "message_id"

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            manager.createNotificationChannel(channel)
        }

        val builder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(title)
            .setContentTitle(description)
            .setSmallIcon(R.drawable.ic_notifications_24)
        manager.notify(1, builder.build())
    }
}