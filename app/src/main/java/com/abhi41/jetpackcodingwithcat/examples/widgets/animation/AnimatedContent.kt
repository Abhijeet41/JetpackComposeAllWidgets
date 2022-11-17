package com.abhi41.jetpackcodingwithcat.examples.widgets.animation

import androidx.compose.animation.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun AnimatedContentDemo() {
    var isVisible by remember {
        mutableStateOf(false)
    }

    AnimatedContent(
        modifier = Modifier
            .fillMaxWidth()
            .height(350.dp),
        targetState = isVisible,
        content = { isVisible ->
            if (isVisible) {
                Box(modifier = Modifier.background(Color.Green))
            } else {
                Box(modifier = Modifier.background(Color.Red))
            }
        },
        transitionSpec = {
           // fadeIn() with fadeOut()
            slideInHorizontally(
                initialOffsetX = { it
                    -it
                }
            ) with slideOutHorizontally(
                targetOffsetX = { it
                    it
                }
            )
        }
    )

}