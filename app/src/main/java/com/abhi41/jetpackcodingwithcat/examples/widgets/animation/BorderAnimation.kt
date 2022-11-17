package com.abhi41.jetpackcodingwithcat.examples.widgets.animation

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BorderAnimation() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var isVisible by remember {
            mutableStateOf(false)
        }

        var isRound by remember {
            mutableStateOf(false)
        }

        Button(onClick = {
            isVisible = !isVisible
            isRound = !isRound
        }) {
            Text(text = "Toggle")
        }

        val borderRadius by animateIntAsState(
            // targetValue = if (isRound) 100 else 0,
            /* animationSpec = tween(
                 durationMillis = 1000,
                 delayMillis = 500,
             )*/
            targetValue = if (isRound) 40 else 20,
            animationSpec = spring(
                dampingRatio = Spring.DampingRatioHighBouncy,
                stiffness = Spring.StiffnessVeryLow
            )
        )

        val transition = updateTransition(
            targetState = isVisible,
            label = null
        )
        val borderRadius2 by transition.animateInt(
            transitionSpec = { tween(2000) },
            label = "borderRadius",
            targetValueByState = { isRound ->
                if (isRound) 100 else 0
            }
        )
        val color by transition.animateColor(
            transitionSpec = { tween(1000) },
            label = "color",
            targetValueByState = { isRound->
                if (isRound) Color.Green else Color.Red
            }
        )

        Box(
            modifier = Modifier
                .size(200.dp)
                .clip(RoundedCornerShape(borderRadius2))
                .background(color)
        )
    }
}