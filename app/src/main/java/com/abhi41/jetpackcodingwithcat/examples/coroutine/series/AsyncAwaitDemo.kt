package com.abhi41.jetpackcodingwithcat.examples.coroutine.series

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

private const val TAG = "AsyncAwaitDemo"

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun AsyncAwaitDemo() {


    //------------------  this is a wrong way to get result from api  ---------------------
    //with this approach it tooks 6 sec thats not we want
    GlobalScope.launch(Dispatchers.IO) {

        val time = measureTimeMillis {
            val answer1 = networkCall()
            val answer2 = networkCall2()
            Log.d(TAG, "Answer is $answer1")
            Log.d(TAG, "Answer is $answer2")
        }
        Log.d(TAG, "Requests took $time ms.") //request time took 6 seconds

    }

    //--------------- this is a right way to get result from api --------------------------
    //with the help of async we can get results in 3 seconds
    GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            val answer1 = async { networkCall() }
            val answer2 = async { networkCall2() }
            Log.d(TAG, "Answer is ${answer1.await()}")
            Log.d(TAG, "Answer is ${answer2.await()}")
        }
        Log.d(TAG, "Requests took $time ms.") //request time took 3 seconds
    }
    //we can also write like this
    GlobalScope.launch(Dispatchers.IO) {
        val time = measureTimeMillis {
            val listOfDocs = listOf(
                async { networkCall() },
                async { networkCall2() }
            )
            listOfDocs.awaitAll()
        }
        Log.d(TAG, "Requests took $time ms.") //request time took 3 seconds
    }

}

private suspend fun networkCall(): String {
    delay(3000L)
    return "response from 1st network call"
}

private suspend fun networkCall2(): String {
    delay(3000L)
    return "response from 2nd network call"
}