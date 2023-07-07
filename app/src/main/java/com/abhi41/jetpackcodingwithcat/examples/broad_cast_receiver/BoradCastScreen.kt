package com.abhi41.jetpackcodingwithcat.examples.broad_cast_receiver

import android.annotation.SuppressLint
import android.content.Intent
import android.content.IntentFilter
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleEventObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "BoradCastScreen"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun BoradCastScreen() {
    val myReceiver = MyReceiver()
    val context = LocalContext.current
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val lastestLifecycleEvent = remember {
        mutableStateOf(Lifecycle.Event.ON_ANY)
    }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit){
        //these are dynamic broadcast receiver
        val intentFilter = IntentFilter()
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED)
        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED)
        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED)
        context.registerReceiver(myReceiver,intentFilter)
    }
    DisposableEffect(lifecycle){
        val observer = LifecycleEventObserver{ _, event ->
            lastestLifecycleEvent.value = event
        }
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }
    when(lastestLifecycleEvent.value){
        Lifecycle.Event.ON_STOP ->{
          context.unregisterReceiver(myReceiver)
        }
        Lifecycle.Event.ON_CREATE ->{
            coroutineScope.launch {
                rebootStatusTask()
            }
        }
    }

}
private suspend fun rebootStatusTask(){
    delay(5000)
    Log.d(TAG, "finish task")
}