package com.abhi41.jetpackcodingwithcat.examples.coroutine.series

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.coroutines.*

private const val TAG = "JobsWaitingCancelation"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun JobsWaitingCancellation() {

    val job = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            Log.d(TAG, "Coroutine is still working... ")
            delay(5000L)
        }
    }

    runBlocking {
        job.join()
        Log.d(TAG, "Main Thred is continuing...")
    }

    //------------------ job cancellation ------------------------

    val job2 = GlobalScope.launch(Dispatchers.Default) {
        repeat(5) {
            Log.d(TAG, "Coroutine is still working... ")
            delay(1000L)
        }
    }

    runBlocking {
        delay(2000L)
        job2.cancel()
        Log.d(TAG, "Main Thred is continuing...")
    }

    //-----------------------job cancellation while using loop Manually---------------------------------

    val job3 = GlobalScope.launch(Dispatchers.Default) {
        Log.d(TAG, "Starting long running calculation...")
        for (i in 30..40) {
            if (isActive) {
                Log.d(TAG, "Result for i = $i: ${fib(i)}")
            }
        }
    } //we are cancelling our job manually by calling job3.cacel()
    runBlocking {
        delay(2000L)
        job3.cancel()
        Log.d(TAG, "Main Thread is continuing...")
    }

//-------------------job cancellation automatically while using loop----------------------------
    val job4 = GlobalScope.launch(Dispatchers.Default) {
        Log.d(TAG, "Starting long running calculation...")

        //with the help of withTimeout we can canecl it automatically after 3 sec
        withTimeout(3000L){
            for (i in 30..40) {
                if (isActive) {
                    Log.d(TAG, "Result for i = $i: ${fib(i)}")
                }
            }
        }
        Log.d(TAG, "Ending long running calculation...")
    }
}

fun fib(n: Int): Long {
    return if (n == 0) 0
    else if (n == 1) 1
    else fib(n - 1) + fib(n - 1)
}


