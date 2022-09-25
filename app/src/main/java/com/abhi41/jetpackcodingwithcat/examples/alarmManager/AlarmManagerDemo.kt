package com.abhi41.jetpackcodingwithcat.examples.alarmManager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun AlarmManagerDemo() {
    Surface(color = MaterialTheme.colors.background) {
        val context = LocalContext.current
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = "Set Alarm Time : 5 secods",
                fontSize = 16.sp
            )
            Button(onClick = {
                setAlarm(context)
            }) {
                Text(text = "Set Alarm")
            }

            Button(onClick = {
                cancelAlarm(context)
            }) {
                Text(text = "Cancel Alarm")
            }

        }
    }
}

private fun setAlarm(context: Context) {
    //show notification after 5 seconds
    val timeSec = System.currentTimeMillis() + 5000
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val intent = Intent(context, AlarmReceiver::class.java)
    // var pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0)

    var pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE)
    } else {
        PendingIntent.getBroadcast(context, 0, intent, 0)
    }

    // alarmManager.set(AlarmManager.RTC_WAKEUP, timeSec, pendingIntent)
    alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeSec, 60000, pendingIntent)
    //RTC_WAKEUP it will wake our phone if its off
    /*
        Note for repeate alarm
        If you're testing on a device with a SDK equal or
         greater than 5.1, your interval alarm cannot be less than 1 minute.
     */
}

private fun cancelAlarm(context: Context) {
    val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
    val myIntent = Intent(context,
        AlarmReceiver::class.java)
    var pendingIntent = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_MUTABLE)
    } else {
        PendingIntent.getBroadcast(context, 0, myIntent, 0)
    }

    alarmManager.cancel(pendingIntent)
}