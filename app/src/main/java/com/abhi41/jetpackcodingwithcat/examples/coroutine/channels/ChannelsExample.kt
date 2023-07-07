package com.abhi41.jetpackcodingwithcat.examples.coroutine.channels

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ChannelsExample() {
    val channelViewmodel: ChannelDemoViewmodel = viewModel()
}