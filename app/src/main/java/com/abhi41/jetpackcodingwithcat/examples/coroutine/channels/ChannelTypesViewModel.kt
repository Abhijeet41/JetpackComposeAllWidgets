package com.abhi41.jetpackcodingwithcat.examples.coroutine.channels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.channels.produce
import kotlinx.coroutines.launch

class ChannelTypesViewModel: ViewModel() {

    private var channel: ReceiveChannel<Language> = Channel()
    private val TAG = "ChannelDemoViewmodel"

    init {
        viewModelScope.launch {
            channel = produce (capacity = 2){
                send(Language.Kotlin)
                Log.d(TAG, "Kotlin Sent!")
                send(Language.Java)
                Log.d(TAG, "Java Sent!")
                send(Language.Python)
                Log.d(TAG, "Python Sent!")
                send(Language.GoLang)
                Log.d(TAG, "GoLang Sent!")
            }
        }

        viewModelScope.launch {
            channel.consumeEach {
                Log.d(TAG, "${it.name}")
            }
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