package com.abhi41.jetpackcodingwithcat.examples.dialogBox

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.DialogProperties
import com.abhi41.jetpackcodingwithcat.R

@Composable
fun CustomAlertDialogExampleActivity() {

    var dialogState by remember {
        mutableStateOf(false)
    }

    Button(
        modifier = Modifier.padding(20.dp),
        onClick = {
            dialogState = true
        },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Red,
            contentColor = Color.White
        )
    ) {
        Text(text = "Custom Dialog")
    }
    CustomAlertDialog(
        dialogState = dialogState,
        onDismissRequest = { state ->
            dialogState = !state
        }
    )
}

@Composable
fun CustomAlertDialog(
    dialogState: Boolean,
    onDismissRequest: (dialogState: Boolean) -> Unit
) {

    if (dialogState) {
        AlertDialog(
            backgroundColor = Color.DarkGray,
            onDismissRequest = {
            },
            title = null,
            text = null,
            buttons = {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    Image(
                        modifier = Modifier
                            .size(200.dp)
                            .clip(CircleShape),
                        painter = painterResource(id = R.drawable.abhijeet),
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                    )
                    Spacer(modifier = Modifier.padding(vertical = 20.dp))
                    Text(
                        text = "Coding with cat",
                        fontSize = 16.sp,
                        color = Color.White
                    )
                    Text(
                        text = "Subscribe coding with cat is helpful",
                        fontSize = 12.sp,
                        color = Color.LightGray
                    )
                    Spacer(modifier = Modifier.padding(vertical = 16.dp))
                    Divider(
                        modifier = Modifier.padding(16.dp),
                        color = Color.White,
                        thickness = 0.8.dp
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        val context = LocalContext.current
                        TextButton(
                            modifier = Modifier
                                .height(40.dp)
                                .weight(1f),
                            onClick = {
                                onDismissRequest(dialogState)
                                Toast.makeText(context, "Nice...", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color.Red,
                                contentColor = Color.White
                            )
                        ) {
                            Text(
                                text = "Subscribe",
                                textAlign = TextAlign.Center,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }
                        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
                        TextButton(
                            modifier = Modifier
                                .height(40.dp)
                                .weight(1f),
                            onClick = {
                                onDismissRequest(dialogState)
                                Toast.makeText(context, "-_-.....", Toast.LENGTH_SHORT).show()
                            },
                            colors = ButtonDefaults.textButtonColors(
                                backgroundColor = Color.DarkGray,
                                contentColor = Color.LightGray
                            )
                        ) {
                            Text(text = "Cancel")
                        }
                    }
                }
            },
            properties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            ),
            shape = RoundedCornerShape(8.dp)
        )
    }

}

