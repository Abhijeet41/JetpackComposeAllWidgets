package com.abhi41.jetpackcodingwithcat.examples.coroutine.series

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.*

private const val TAG = "CoroutineContext"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CoroutineContext() {
    val context = LocalContext.current
    GlobalScope.launch(Dispatchers.IO) {
        val answer = doNetworkCall()
        Log.d(TAG, "Thread is : ${Thread.currentThread().name}")
        withContext(Dispatchers.Main) {
            //we can use ui related stuff inside our main thread
            Log.d(TAG, "Thread is : ${Thread.currentThread().name}")
            Toast.makeText(context, answer, Toast.LENGTH_SHORT).show()
        }
    }


}

private suspend fun doNetworkCall(): String {
    delay(3000L)
    return "This is response"
}