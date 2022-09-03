package com.abhi41.jetpackcodingwithcat.examples.notification

import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abhi41.jetpackcodingwithcat.di.DefaultNotificationCompactBuilder
import com.abhi41.jetpackcodingwithcat.di.DownloadNotificationCompactBuilder
import com.abhi41.jetpackcodingwithcat.di.ReplyMessageNotificationBuilder
import com.abhi41.jetpackcodingwithcat.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    @DefaultNotificationCompactBuilder
    private val defaultNotificationBuilder: NotificationCompat.Builder,
    @DownloadNotificationCompactBuilder
    private val downloadNotificationBuilder: NotificationCompat.Builder,

    @ReplyMessageNotificationBuilder
    private val replyMessageNotificationBuilder: NotificationCompat.Builder,

    private val notificationManager: NotificationManagerCompat,
) : ViewModel() {

    fun showSimpleNotification() {
        notificationManager.notify(
            Constants.DEFAULT_NOTIFICATION_ID,
            defaultNotificationBuilder.build()
        )
    }

    fun showReplyNotification(){
        notificationManager.notify(
            Constants.REPLY_NOTIFICATION_ID,
            replyMessageNotificationBuilder.build()
        )
    }

    fun updateSimpleNotification(title: String, description: String) {
        notificationManager.notify(Constants.NOTIFICATION_UPDATED_ID,
            defaultNotificationBuilder
                .setContentTitle(title)
                .setContentText(description)
                .build()
        )
    }

    //show download progress over notification
    fun showDownloadProgressNotification() {
        val max = 10 //file size
        var progress = 0

        viewModelScope.launch {
            while (progress != max) {
                delay(1000)
                progress += 1
                notificationManager.notify(
                    Constants.DOWNLOAD_NOTIFICATION_ID,
                    downloadNotificationBuilder
                        .setContentTitle("Downloading")
                        .setContentText("${progress}/${max}")
                        .setProgress(max, progress, false)
                        .build()
                )
            }
            notificationManager.notify(
                Constants.DOWNLOAD_NOTIFICATION_ID,
                downloadNotificationBuilder
                    .setContentTitle("Completed!")
                    .setContentText("")
                    .setContentIntent(null)
                    .setOngoing(false)
                    .clearActions()
                    .setProgress(0,0,false)
                    .build()
            )
        }

    }

    fun cancelSimpleNotification() {
        notificationManager.cancel(Constants.DEFAULT_NOTIFICATION_ID)
    }

}