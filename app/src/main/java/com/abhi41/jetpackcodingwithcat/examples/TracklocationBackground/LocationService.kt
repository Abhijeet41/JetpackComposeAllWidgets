package com.abhi41.jetpackcodingwithcat.examples.TracklocationBackground

import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.abhi41.jetpackcodingwithcat.R
import com.abhi41.jetpackcodingwithcat.di.DefaultNotificationCompactBuilder
import com.abhi41.jetpackcodingwithcat.di.ReplyMessageNotificationBuilder
import com.abhi41.jetpackcodingwithcat.util.Constants
import com.abhi41.jetpackcodingwithcat.util.Constants.LOCATION_NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class LocationService : Service() {

    @DefaultNotificationCompactBuilder
    @Inject
    lateinit var notificationBuilder: NotificationCompat.Builder
    @Inject
    lateinit var locationClient: LocationClient

    @Inject
    lateinit var notificationManager: NotificationManagerCompat



    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    //this service scope is bind to lifecycle of our service when service killed our scope is also destroyed

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        //Insted of this use dagger-hilt
        /* locationClient = DefaultLocationClient(
             applicationContext,
             LocationServices.getFusedLocationProviderClient(applicationContext)
         )*/
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun start() {

        val notification = notificationBuilder
            .setContentTitle("Tracking location...")
            .setContentText("Location: null")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setOngoing(true)

        locationClient.getLocationUpdates(10000L) //10 sec
            .catch { e ->
                e.printStackTrace()
            }.onEach { location ->
                val lat = location.latitude.toString()
                val long = location.longitude.toString()

                val updatedNotification = notification.setContentText(
                    "Location: ($lat,$long)"
                )
                notificationManager.notify(LOCATION_NOTIFICATION_ID, updatedNotification.build())
            }
            .launchIn(serviceScope)
        startForeground(LOCATION_NOTIFICATION_ID, notification.build())
    }

    private fun stop() {
        stopForeground(true) //remove notification when stop
        stopSelf()// to stop our service
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
    }

}