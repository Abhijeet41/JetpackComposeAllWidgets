package com.abhi41.jetpackcodingwithcat.di

import android.app.*
import android.app.Notification.VISIBILITY_PUBLIC
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.app.Person
import androidx.core.app.RemoteInput
import androidx.core.net.toUri
import com.abhi41.jetpackcodingwithcat.MainActivity
import com.abhi41.jetpackcodingwithcat.R
import com.abhi41.jetpackcodingwithcat.receiver.MyNotificationReceiver
import com.abhi41.jetpackcodingwithcat.util.Constants
import com.abhi41.jetpackcodingwithcat.util.Constants.CHANNEL_ID_WORKER
import com.abhi41.jetpackcodingwithcat.util.Constants.CHANNEL_NAME
import com.abhi41.jetpackcodingwithcat.util.Constants.DETAIL_SCREEN
import com.abhi41.jetpackcodingwithcat.util.Constants.MY_URI
import com.abhi41.jetpackcodingwithcat.util.Constants.WORKER_NOTIFICATION_ID
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

const val RESULT_KEY = "RESULT_KEY"

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Singleton
    @Provides
    @DefaultNotificationCompactBuilder
    fun provideNotificationBuilder(
        @ApplicationContext context: Context,
    ): NotificationCompat.Builder {

        val intent = Intent(context, MyNotificationReceiver::class.java).apply {
            putExtra("message", "Clicked!")
        }
        val flag = // we need to define this flag other wise its show error for 31 above devices
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
                PendingIntent.FLAG_IMMUTABLE
            else 0
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            flag
        )
        //val clickIntent = Intent(context,MainActivity::class.java)
        val clickIntent = Intent(
            Intent.ACTION_VIEW,
            //"$MY_URI/message=Coming from notification".toUri(), //this one is without route param
            "$MY_URI$DETAIL_SCREEN/message=Coming from notification".toUri(),
            context,
            MainActivity::class.java
        )
        val clickPendingIntent = PendingIntent.getActivity(
            context, 1, clickIntent, flag
        )

        return NotificationCompat.Builder(context, Constants.CHANNEL_ID)
            .setContentTitle("Welcome")
            .setContentText("Youtube Channel: Stevdza-San")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setPublicVersion(
                NotificationCompat.Builder(context, Constants.CHANNEL_ID)
                    .setContentTitle("Hidden")
                    .setContentText("Unlock to see the message.")
                    .build()
            ).addAction(0, "ACTION", pendingIntent)
            .setContentIntent(clickPendingIntent)
    }


    @Singleton
    @Provides
    @DownloadNotificationCompactBuilder
    fun provideDownloadNotificationBuilder(
        @ApplicationContext context: Context,
    ): NotificationCompat.Builder {
        return NotificationCompat.Builder(context, Constants.CHANNEL_ID_DOWNLOAD)
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_LOW) //not to trigger notification sound
            .setOngoing(true) //to prevent user to remove notification

    }

    //------------------- reply notification message--------------------------------------

    @Singleton
    @Provides
    @ReplyMessageNotificationBuilder
    fun provideNotificationReply(
        @ApplicationContext context: Context,
    ): NotificationCompat.Builder {

        val flag = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            PendingIntent.FLAG_MUTABLE
        } else {
            0
        }
        val remoteInput = RemoteInput.Builder(RESULT_KEY)
            .setLabel("Type here...")
            .build()

        val replyIntent = Intent(context, MyNotificationReceiver::class.java)
        val replyPendingIntent = PendingIntent.getBroadcast(
            context,
            1,
            replyIntent,
            flag
        )
        val replyAction = NotificationCompat.Action.Builder(
            0,
            "Reply", replyPendingIntent
        ).addRemoteInput(remoteInput).build()
        val person = Person.Builder().setName("Abhijeet").build()
        val notificationStyle = NotificationCompat.MessagingStyle(person)
            .addMessage("Hi there!", System.currentTimeMillis(), person)
        return NotificationCompat.Builder(context, "Main Channel ID")
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setOnlyAlertOnce(true)
            .setStyle(notificationStyle)
            .addAction(replyAction)
    }

    @Singleton
    @Provides
    @WorkerNotificationBuilder
    fun createWorkManagerNotification(
        @ApplicationContext ctx: Context,
        title: String,
        description: String
    ): NotificationCompat.Builder {
        val builder = NotificationCompat.Builder(ctx, Constants.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_notifications_24)
            .setContentTitle(title)
            .setContentText(description)
            .setOngoing(true)
            .setAutoCancel(true)

        return builder
    }

    @RequiresApi(Build.VERSION_CODES.O)
    @Singleton
    @Provides
    fun provideNotificationManager(
        @ApplicationContext context: Context,
    ): NotificationManagerCompat {
        val notificationManager = NotificationManagerCompat.from(context)
        val channel = NotificationChannel(
            Constants.CHANNEL_ID,
            Constants.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        /* we have to use second channel because when ever we use progressbar we don't want to make
            notification sound
         */
        val chanel_download = NotificationChannel(
            Constants.CHANNEL_ID_DOWNLOAD,
            Constants.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW //to avoid notification sound
        )
        //this channel is specific for location tracking
        val channel_location = NotificationChannel(
            Constants.CHANNEL_ID_LOCATION,
            Constants.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_LOW
        )
        val channel_worker = NotificationChannel(
            Constants.CHANNEL_ID_WORKER,
            Constants.CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )

        notificationManager.createNotificationChannel(channel)
        notificationManager.createNotificationChannel(chanel_download)
        notificationManager.createNotificationChannel(channel_location)
        notificationManager.createNotificationChannel(channel_worker)

        return notificationManager
    }

}


//provide multiple bindings for the same type
/*
    Note: we have to function which is having same return type provideNotificationBuilder and
    provideDownloadNotificationBuilder so to avoid conflict we use @Qualifier and @Retention
 */

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DefaultNotificationCompactBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class DownloadNotificationCompactBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ReplyMessageNotificationBuilder

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class WorkerNotificationBuilder