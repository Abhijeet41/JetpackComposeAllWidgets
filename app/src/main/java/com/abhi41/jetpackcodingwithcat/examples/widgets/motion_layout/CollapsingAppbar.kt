@file:OptIn(ExperimentalMotionApi::class)

package com.abhi41.jetpackcodingwithcat.examples.widgets.motion_layout

import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.abhi41.jetpackcodingwithcat.R
import com.ramcosta.composedestinations.spec.DestinationStyle.Dialog.Default.properties

@Composable
fun CollapsingAppbar() {
    Column {
        var progress by remember {
            mutableStateOf(0f)
        }
        ProfileHeader(progress = progress)
        Spacer(modifier = Modifier.height(32.dp))
        Slider(
            modifier = Modifier.padding(horizontal = 32.dp),
            value = progress,
            onValueChange = {
                progress = it
            }
        )
    }
}

@Composable
fun ProfileHeader(progress: Float) {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier.fillMaxWidth()
    ) {
        val properties = motionProperties(id = "profile_pic")
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.DarkGray)
                .layoutId("box")
        )
        Image(
            painter = painterResource(id = R.drawable.abhijeet),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .border(
                    width = 2.dp,
                    color = properties.value.color("background"),
                    shape = CircleShape
                )
                .layoutId("profile_pic")
        )
        Text(
            text = "Abhijeet Mule",
            fontSize = 24.sp,
            modifier = Modifier.layoutId("username"),
            color = properties.value.color("background")
        )
    }


}