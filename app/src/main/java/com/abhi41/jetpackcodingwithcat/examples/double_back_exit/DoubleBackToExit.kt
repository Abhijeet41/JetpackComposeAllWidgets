package com.abhi41.jetpackcodingwithcat.examples.double_back_exit

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import kotlinx.coroutines.delay

@Composable
fun DoubleBackToExit() {
    var showToast by remember { mutableStateOf(false) }

    var backPressState by remember { mutableStateOf(BackPress()) }
    val context = LocalContext.current

    if(showToast){
        Toast.makeText(context, "Press again to exit", Toast.LENGTH_SHORT).show()
        showToast= false
    }

    LaunchedEffect(key1 = backPressState) {
        if (!backPressState.isBackPressed) {
            delay(2000)
            backPressState = backPressState.copy(isBackPressed = true)
        }
    }
    BackHandler(!backPressState.isBackPressed) {
        //backPressState = BackPress.InitialTouch
        showToast = true
    }

}