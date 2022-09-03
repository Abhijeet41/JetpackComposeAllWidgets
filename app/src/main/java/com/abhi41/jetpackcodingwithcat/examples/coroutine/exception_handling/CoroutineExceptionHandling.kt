package com.abhi41.jetpackcodingwithcat.examples.coroutine

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.*

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun CoroutineExceptionHandling() {
    val lifecycleScope = rememberCoroutineScope()
    val handler = CoroutineExceptionHandler { _, throwable ->
        println("Caught exception: $throwable")
    }

    lifecycleScope.launch(handler) {
        launch {
            throw Exception("Error")
        }
    }
    //this is an example of custom coroutine try to use viewmodelScope coroutine
//--------------------------CoroutineScope--------------------------------------------
    /*
        Note: In Coroutine Scope if one coroutine is failed no matter if you exception
        handled or not it will cancel all its child coroutine
     */

    CoroutineScope(Dispatchers.Main + handler).launch {
        launch {
            delay(300L)
            throw Exception("Coroutine 1 failed")
        }
        launch {
            delay(400L)
            println("Coroutine 2 finished")
        }
    }

    /*
         so to prevent above issue we can use supervisorScope so if 1st scope
        throws exception then it will not affect the other scope, hence 2nd scope will get executed
     */

    CoroutineScope(Dispatchers.Main + handler).launch {
        //if 1st coroutine failed then it will not affect 2nd coroutine. other coroutine will get executed.
        supervisorScope {
            launch {
                delay(300L)
                throw Exception("Coroutine 1 failed")
            }

            launch {
                delay(400L)
                println("Coroutine 2 finished ")
            }
        }

        //if 1st coroutine get exception then other coroutine will also gets failed like coroutine 2
        coroutineScope {
            launch {
                delay(300L)
                throw Exception("Coroutine 1 failed")
            }

            launch {
                delay(400L)
                println("Coroutine 2 finished ")
            }
        }
    }

}