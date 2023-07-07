package com.abhi41.jetpackcodingwithcat.examples.coroutine.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.*
import kotlinx.coroutines.launch

class ChannelDemoViewmodel : ViewModel() {

    //In this demo we received c

    // private var channel = Channel<Language>()
    private var channel: ReceiveChannel<Language> = Channel()
    private val TAG = "ChannelDemoViewmodel"

    init {
        // Coroutine #1
        viewModelScope.launch {
            Log.d(TAG, "${channel.isClosedForReceive}")
            channel = produce {
                send(Language.Java)
            }
            Log.d(TAG, "${channel.isClosedForReceive}")
        }
        //Coroutine #2
        viewModelScope.launch {
            //this is to verify channel is closed or not
            Log.d(TAG, "${channel.isClosedForReceive}")
            //this for receive kotlin
            Log.d(TAG, channel.receive().toString())
            //this for receive Java
            Log.d(TAG, channel.receive().toString())
            //after executing this code o/p will be
            /* kotlin Sent!
                kotlin
                Java Sent
                Java
             */
        }
        //this is second method to receive data from channel
        //by this method we don't need to worry about to close channel
        viewModelScope.launch {
            channel.consumeEach {
                Log.d(TAG, "${it.name}")
            }
        }

    }

   private enum class Language {
        Kotlin,
        Java,
        Python,
        CSharp,
        GoLang
    }
}

