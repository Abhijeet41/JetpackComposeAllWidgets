package com.abhi41.jetpackcodingwithcat.examples.widgets.animation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun BasicAnimation() {
    Column(modifier = Modifier.fillMaxSize()) {

        var isVisible by remember {
            mutableStateOf(false)
        }

        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Toggle")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally() + fadeIn(),
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
        ) {
            Box(modifier = Modifier.background(Color.Red))
        }

    }
}