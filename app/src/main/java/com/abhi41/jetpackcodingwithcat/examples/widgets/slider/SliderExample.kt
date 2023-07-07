package com.abhi41.jetpackcodingwithcat.examples.widgets.slider

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Slider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

@Composable
fun SliderExample() {

    // SliderWithUnnecessaryRecomposition()
    SliderWithProperComposition()
}

//this will emit value unnecessarily even value is less than 20
@Composable
fun SliderWithUnnecessaryRecomposition() {
    var sliderValue by remember { mutableStateOf(0f) }
    val isBellowRange = sliderValue < 20f

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            valueRange = 0f..100f
        )

        Text(
            "Slider At ${sliderValue.roundToInt()}",
            color = if (isBellowRange) Color.Red else Color.Black
        )

    }
}


//derived state emits value only when slider value is less than 20
@Composable
fun SliderWithProperComposition() {
    var sliderValue by remember { mutableStateOf(0f) }

    val isBellowRange by remember {
        derivedStateOf { sliderValue < 20f }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center
    ) {

        Slider(
            value = sliderValue,
            onValueChange = {it
                sliderValue = it

            },
            steps = 10,
            // valueRange = 0f..100f
            valueRange = 0f..100f,

        )

        Text(
            "Slider At ${sliderValue.roundToInt()}",
            color = if (isBellowRange) Color.Red else Color.Black
        )

    }
}