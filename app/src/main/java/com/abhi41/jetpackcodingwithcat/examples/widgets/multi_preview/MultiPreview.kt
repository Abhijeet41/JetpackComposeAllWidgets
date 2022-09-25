package com.abhi41.jetpackcodingwithcat.examples.widgets.multi_preview

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MultiPreview() {

    Scaffold(
        topBar = {
            TopAppBar(
                title = { "MultiplePreview" },
                contentColor = Color.White,
                backgroundColor = Color.Black
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp)
            ) {
                MyCard()
            }
        }
    )

}

@Composable
fun MyCard() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(size = 12.dp)),
        contentAlignment = Alignment.Center
    ) {

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Android", fontSize = 28.sp,
                color = Color.Black,
            )
            Text(
                modifier = Modifier.padding(top = 18.dp),
                text = "Developer Mod : On", fontSize = 18.sp,
                color = Color.Black,
            )
        }

    }

}

@Composable
@FontScapePreviews
@DevicePreviews
fun MyCardPreview() {
    MyCard()
}