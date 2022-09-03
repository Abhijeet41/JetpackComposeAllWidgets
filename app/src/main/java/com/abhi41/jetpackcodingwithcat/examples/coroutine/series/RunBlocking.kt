package com.abhi41.jetpackcodingwithcat.examples.coroutine.series

import android.util.Log
import androidx.compose.runtime.Composable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

private const val TAG = "RunBlocking"
@Composable
fun RunBlocking() {
/*
    we cannot use suspend function or delay function inside our main thread
    but if we want to delay our main thread for 5 sec then we should use runblocking
 */
    Log.d(TAG, "Before runblocking: ")
    runBlocking {
        /* this two coroutines are execute async because they are in different coroutine scope.
            so after 3 sec both coroutine are finished at same time
         */
        launch (Dispatchers.IO){
            delay(3000L)
            Log.d(TAG, "Finish coroutine 1")
        }
        launch (Dispatchers.IO){
            delay(3000L)
            Log.d(TAG, "Finish coroutine 2")
        }

        Log.d(TAG, "Start of runblocking: ")
        delay(5000L)
        Log.d(TAG, "End of runblocking: ")
    }
    Log.d(TAG, "After runblocking: ")

}