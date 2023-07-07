package com.abhi41.jetpackcodingwithcat.examples.broad_cast_receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import java.lang.Thread.State

class MyReceiver : BroadcastReceiver() {
    private  val TAG = "MyReceiver"
    override fun onReceive(context: Context, intent: Intent) {
       val actionString = intent.action
        Toast.makeText(context,actionString,Toast.LENGTH_LONG).show()
        val timeZone = intent.getStringExtra("time-zone")
        Log.d(TAG, "onReceive: $timeZone")

        val airplaneModeStatus = intent.getBooleanExtra("state",false)
        Log.d(TAG, "onReceive: airplane mode is On: $airplaneModeStatus")
    }
}