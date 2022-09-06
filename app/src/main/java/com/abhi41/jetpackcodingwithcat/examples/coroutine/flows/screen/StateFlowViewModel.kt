package com.abhi41.jetpackcodingwithcat.examples.coroutine.flows.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class StateFlowViewModel :ViewModel(){
    
    private val _stateFlow = MutableStateFlow(0)
    val stateFlow = _stateFlow.asStateFlow()

    init {
        //incrementCounter()
    }

    public fun incrementCounter(){
        _stateFlow.value = _stateFlow.value + 1
    }

    //when to use shared flow when you want to emmit one time event
    
}