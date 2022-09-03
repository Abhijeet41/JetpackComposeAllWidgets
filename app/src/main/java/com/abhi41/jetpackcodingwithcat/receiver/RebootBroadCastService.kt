package com.abhi41.jetpackcodingwithcat.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build

class RebootBroadCastService: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action.equals(Intent.ACTION_BOOT_COMPLETED)){
            val intent = Intent(context, RebootBroadCastService::class.java)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                context?.startForegroundService(intent)
            }else{
                context?.startService(intent)
            }
        }
    }
}