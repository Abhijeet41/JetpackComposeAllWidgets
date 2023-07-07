@file:OptIn(ExperimentalComposeUiApi::class)

package com.abhi41.jetpackcodingwithcat.examples.widgets.textfield

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun CustomTextFieldActivity() {
    Column(modifier = Modifier.fillMaxWidth()) {
        CustomTextField()
        OutLineTextFieldExample()
    }
}

@Composable
fun CustomTextField() {
    var text by remember {
        mutableStateOf("")
    }

    val keyboardController = LocalSoftwareKeyboardController.current
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.LightGray)
            .padding(10.dp)
    ) {
        BasicTextField(
            value = text, onValueChange = {
                text = it
            }, modifier = Modifier
                .background(Color.White, CircleShape)
                .height(46.dp)
                .fillMaxWidth(),
            maxLines = 1,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { }) {
                        Icon(Icons.Filled.Face, null)
                    }

                    Box(
                        modifier = Modifier.weight(1f),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        innerTextField()
                    }

                    IconButton(onClick = {
                        keyboardController?.hide()
                    }) {
                        Icon(Icons.Filled.Send, null)
                    }
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    keyboardController?.hide()
                }
            )
        )
    }
}

@Composable
private fun OutLineTextFieldExample() {
    var text by remember {
        mutableStateOf("")
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    OutlinedTextField(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        label = {
            Text(text = "I'm the label", color = Color.Blue)
        },
        leadingIcon = {
            Icon(Icons.Filled.Email, null, tint = Color.Blue)
        },
        trailingIcon = {
            Text(
                modifier = Modifier.padding(end = 10.dp),
                text = "@xxx.com",
                color = Color.Blue,
            )
        },
        placeholder = {
            Text(text = "hint text here")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
            }
        ),
        maxLines = 1,
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Blue,
            backgroundColor = Color.Transparent,
            cursorColor = Color.Blue,
            focusedIndicatorColor = Color.Blue,
            unfocusedIndicatorColor = Color.Gray,
            focusedLabelColor = Color.Black,
            unfocusedLabelColor = Color.Blue,
            placeholderColor = Color.LightGray
        ),
        shape = MaterialTheme.shapes.medium
    )
}
