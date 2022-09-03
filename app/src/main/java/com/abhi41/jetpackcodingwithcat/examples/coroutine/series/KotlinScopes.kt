package com.abhi41.jetpackcodingwithcat.examples.coroutine.series

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private const val TAG = "KotlinScopes"

@Composable
fun KotlinScopes() {
    globalScope()
}

fun globalScope (){
    GlobalScope.launch {
        //we can call delay fun only in suspend function or in scope
        delay(3000L)
        Log.d(TAG, Thread.currentThread().name.toString())  //DefaultDispatcher-worker-1
    }
    //when we delayed global scope for 3 sec it will not affect the below print.
    Log.d("Outside Global Scope", Thread.currentThread().name.toString())

    GlobalScope.launch {
        /* this network call and database call are executed after 6 seconds because they are
            executed at the same Global scope, they are synchronous with each other
         */
        val netWorkCall = doNetworkCall()
        val databaseCall = doDatabaseCall()

        //so this log will print after 6 seconds because both are in same scope
        Log.d(TAG, netWorkCall)
        Log.d(TAG, databaseCall)
    }


}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun lifecyclerScope(
    navigator: DestinationsNavigator
){

    val lifecycleScope = LocalLifecycleOwner.current

    lifecycleScope.lifecycleScope.launch {
        while (true){
            delay(1000L)
            Log.d(TAG, "This is an infinite loop")
        }
    }
    GlobalScope.launch {
        delay(5000L)
        //navigator.navigate(SecondScreen())
        //when we navigate the lifecycle scope will get destroyed because its lifecycle awere
    }

}


private suspend fun doNetworkCall(): String {
    delay(3000L)
    return "This is a response"
}

private suspend fun doDatabaseCall(): String{
    delay(3000L)
    return "This is a database call"
}

