package com.abhi41.jetpackcodingwithcat.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import dagger.hilt.android.AndroidEntryPoint

class MyNotificationReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent) {
        val message = intent.getStringExtra("message")

        if (message != null){
            Toast.makeText(context,message,Toast.LENGTH_SHORT).show()
        }
    }
}