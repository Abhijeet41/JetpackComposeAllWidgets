package com.abhi41.jetpackcodingwithcat.examples.box

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxActivity() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(20.dp))
        BoxExample1()
        Spacer(modifier = Modifier.height(20.dp))
        BoxExample2()
        Spacer(modifier = Modifier.height(20.dp))
        BoxExample3()
    }
}

@Composable
private fun BoxExample1() {
    Box(modifier = Modifier.background(Color.Red)) {
        Text(
            modifier = Modifier.padding(30.dp),
            text = "Box Example 1",
            color = Color.White,
        )
    }
}

@Composable
private fun BoxExample2() {
    Box(
        modifier = Modifier
            .size(120.dp)
            .background(Color.Black)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
                .background(Color.Red)
        )
        Text(
            text = "Example 2",
            color = Color.White,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun BoxExample3() {
    Box(
        modifier = Modifier.size(120.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Blue)
        ) {
            Text(
                text = "Example 3.1",
                color = Color.White,
                modifier = Modifier.align(Alignment.TopCenter)
            )

            Text(
                text = "Example 3.2",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 10.dp, bottom = 10.dp)
            )
        }
    }
}