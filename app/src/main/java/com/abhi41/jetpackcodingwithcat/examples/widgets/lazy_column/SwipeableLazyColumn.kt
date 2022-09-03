@file:OptIn(ExperimentalMaterialApi::class)

package com.abhi41.jetpackcodingwithcat.examples.widgets.lazy_column

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.lang.Math.abs

@SuppressLint("UnrememberedMutableState")
@Composable
fun SwipeableLazyColumn() {

    ContentView()
}

@Composable
fun ListItemView(content: String) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)
        .padding(horizontal = 16.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = content,
            fontSize = 18.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun ContentView() {
    val list = mutableStateListOf<String>()
    list.clear()

    val tempList = mutableListOf<String>()
    for (i in 0 until 50) {
        tempList.add("content $i")
    }
    list.addAll(tempList)

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(
                items = list,
                key = { index: Int, item: String ->
                    item.hashCode()
                }
            ) { index: Int, item: String ->
                val dismissState = rememberDismissState(
                    confirmStateChange = {
                        if (it == DismissValue.DismissedToStart) {
                            list.remove(item)
                        }
                        true
                    }
                )

                SwipeToDismiss(
                    state = dismissState,
                    background = {
                        val color = when (dismissState.dismissDirection) {
                            DismissDirection.StartToEnd -> {
                                Color.Transparent
                            }
                            DismissDirection.EndToStart -> {
                                val r = 1f
                                var a = 1f - (abs(dismissState.offset.value) / 255f) * 0.5f
                                var b = 1f - (abs(dismissState.offset.value) / 255f) * 0.5f

                                if (a <= 0f) {
                                    a = 0f
                                }
                                if (b <= 0f) {
                                    b = 0f
                                }

                                Color(red = r, green = a, blue = b)
                            }
                            else -> {
                                Color.Transparent
                            }
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = color)
                                .padding(10.dp),
                            contentAlignment = Alignment.CenterEnd
                        ) {
                            Icon(
                                Icons.Filled.Delete, null, tint = Color.White
                            )
                        }
                    },
                    directions = setOf(DismissDirection.EndToStart),
                    dismissContent = {
                        ListItemView(content = item)
                    }
                )
            }
        }
    }
}