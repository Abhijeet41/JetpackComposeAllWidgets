package com.abhi41.jetpackcodingwithcat.examples.InternetConnection

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


private lateinit var connectivityObserver: ConnectivityObserver

@Composable
fun ConnectionScreen() {
    val context = LocalContext.current
    connectivityObserver = NetworkConnectivityObserver(context)

    val status by connectivityObserver.observe().collectAsState(
        initial = ConnectivityObserver.Status.Unavailable
    )

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Network Status: $status")
    }

}