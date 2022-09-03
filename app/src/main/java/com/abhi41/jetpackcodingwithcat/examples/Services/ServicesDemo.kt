package com.abhi41.jetpackcodingwithcat.examples.Services

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import java.lang.Character.getName

@Composable
fun ServicesDemo() {
    val context = LocalContext.current

   /* val intent = Intent(context,MyBackgroundService::class.java)
    context.startService(intent)*/

    if (!foregroundServiceRunning(context)){ //check weather service is running or not
        val intent = Intent(context,MyForegroundService::class.java)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            context.startForegroundService(intent)
        }else{
            context.startService(intent)
        }
    }



}
public fun foregroundServiceRunning(context: Context): Boolean{
    val activityManager = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
    for (service in activityManager.getRunningServices(Int.MAX_VALUE)) {
        if (MyForegroundService::class.java.getName().equals(service.service.getClassName())) {
            return true
        }
    }
    return false
}
