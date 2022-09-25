@file:OptIn(ExperimentalComposeUiApi::class)

package com.abhi41.jetpackcodingwithcat.examples.widgets.otpview

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@SuppressLint("UnrememberedMutableState")
@Composable
fun OtpViewScreen() {
    val textList = listOf(
        mutableStateOf(
            TextFieldValue(text = "",
                selection = TextRange(0))
        ),
        mutableStateOf(
            TextFieldValue(text = "",
                selection = TextRange(0))
        ),
        mutableStateOf(
            TextFieldValue(text = "",
                selection = TextRange(0))
        ),
        mutableStateOf(
            TextFieldValue(text = "",
                selection = TextRange(0))
        )
    )
    val requestList = listOf(
        FocusRequester(),
        FocusRequester(),
        FocusRequester(),
        FocusRequester()
    )

    ContentView(textList = textList, requesterList = requestList)

}

@Composable
fun ContentView(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    val context = LocalContext.current

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 50.dp)
                    .align(Alignment.TopCenter)
            ) {
                for (i in textList.indices) {
                    InputView(
                        value = textList[i].value,
                        onValueChange = { newValue ->
                            //if old value is not empty, just return
                            if (textList[i].value.text != ""){
                                if (newValue.text == ""){
                                    //before return, if the new value is empty, set the text field to empty
                                    textList[i].value = TextFieldValue(
                                        text = "",
                                        selection = TextRange(0)
                                    )
                                }
                            }
                            //set new value and move cursor to end
                            textList[i].value = TextFieldValue(
                                text = newValue.text,
                                selection = TextRange(newValue.text.length)
                            )
                            nextFocus(textList,requesterList)
                        },
                        focusRequester = requesterList[i]
                    )
                }
            }
        }
    }

    LaunchedEffect(key1 = null, block = {
        delay(300)
        requesterList[0].requestFocus()
    })

}

private fun nextFocus(
    textList: List<MutableState<TextFieldValue>>,
    requesterList: List<FocusRequester>
){
    for (index in textList.indices){
        if (index < textList.size){
            requesterList[index].requestFocus()
            break
        }
    }
}

@Composable
fun InputView(
    value: TextFieldValue,
    onValueChange: (value: TextFieldValue) -> Unit,
    focusRequester: FocusRequester,
) {

    BasicTextField(
        readOnly = false,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.Gray)
            .wrapContentSize()
            .focusRequester(focusRequester),
        maxLines = 1,
        decorationBox = { innerTextField ->
            Box(modifier = Modifier
                .width(50.dp)
                .height(70.dp),
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        },
        cursorBrush = SolidColor(Color.White),
        textStyle = TextStyle(
            color = Color.White,
            fontSize = 26.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = null
        )

    )

}