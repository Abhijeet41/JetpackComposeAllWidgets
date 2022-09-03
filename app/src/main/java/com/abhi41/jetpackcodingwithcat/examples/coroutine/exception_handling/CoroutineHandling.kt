package com.abhi41.jetpackcodingwithcat.examples.coroutine

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CoroutineHandling() {
    val lifecycleScope = rememberCoroutineScope()
    /*
        In this we can common mistake we done while cancelling child scope
     */

//---------------------------this is a wrong way to cancel the child scope--------------------
    lifecycleScope.launch {
        val job = launch {
            try {
                delay(500L)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            println("Coroutine 1 finished") //it will still print even we cancel job
        }
        delay(300L)
        job.cancel()
    }

    //-------------------this is a right way to cancel child scope-------------------------
    lifecycleScope.launch {
        //so if we have retrofit call and some reason if we want to cancel it then do this
        val job = launch {
            try {
                delay(500L)
            } catch (e: Exception) {
                if (e is CancellationException) {
                    throw e
                }
                e.printStackTrace()
            }
            println("Coroutine 1 finished")
        }
        delay(300L)
        job.cancel()
    }
}