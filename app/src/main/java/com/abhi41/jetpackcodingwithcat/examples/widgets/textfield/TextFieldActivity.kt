@file:OptIn(ExperimentalComposeUiApi::class)

package com.abhi41.jetpackcodingwithcat.examples.widgets.textfield

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.abhi41.jetpackcodingwithcat.R


@Composable
fun TextFieldActivity() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        SimpleTextField()
        Spacer(modifier = Modifier.height(10.dp))
        LabelTextField()
        Spacer(modifier = Modifier.height(10.dp))
        LeadingAndTrailingPartTextField()
        Spacer(modifier = Modifier.height(10.dp))
        ColorTextField()
        Spacer(modifier = Modifier.height(10.dp))
        PasswordTypeTextField()
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        }
    )
}

@Composable
fun LabelTextField() {
    var text by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            label = {
                Text(text = "This is a label", color = Color.Red)
            },
            singleLine = true
        )
    }
}

@Composable
fun LeadingAndTrailingPartTextField() {

    var text by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "",
                    tint = Color.Red
                )
            },
            trailingIcon = {
                Text(text = "@xxx.com", color = Color.Red)
            }
        )
    }
}

@Composable
fun ColorTextField() {
    var text by remember {
        mutableStateOf("")
    }
    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Email,
                    contentDescription = "",
                    tint = Color.Red
                )
            },
            trailingIcon = {
                Text(text = "@xxx.com", color = Color.Red)
            },
            label = {

            },
            placeholder = {
                Text(text = "your email")
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Red,
                backgroundColor = Color.Transparent,
                cursorColor = Color.Red,
                focusedIndicatorColor = Color.Red,
                unfocusedIndicatorColor = Color.Cyan,
                focusedLabelColor = Color.Black,
                unfocusedLabelColor = Color.LightGray,
                placeholderColor = Color.LightGray,
            )
        )
    }
}

@Composable
private fun PasswordTypeTextField() {
    var text by remember {
        mutableStateOf("")
    }

    var passwordHidden by remember {
        mutableStateOf(false)
    }
    val keyboardController = LocalSoftwareKeyboardController.current

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = {
            text = it
        },
        trailingIcon = {
            Row() {
                IconButton(
                    onClick = {
                        passwordHidden = !passwordHidden
                    }
                ) {
                    Icon(
                        painterResource(
                            id = if (passwordHidden)
                                R.drawable.ic_invisible_24 else
                                R.drawable.ic_visible_24
                        ),
                        contentDescription = null
                    )
                }
                IconButton(
                    onClick = {
                        text = ""
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = null
                    )
                }
            }
        },
        label = {
            Text(text = "password")
        },
        visualTransformation = if (passwordHidden) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Search
        ),
        keyboardActions = KeyboardActions(
            onSearch = {
                //hide keyboard
                keyboardController?.hide()
            }
        )
    )
}

