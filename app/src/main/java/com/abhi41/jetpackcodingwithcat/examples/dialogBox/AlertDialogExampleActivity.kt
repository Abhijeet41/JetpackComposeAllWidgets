package com.abhi41.jetpackcodingwithcat.examples.dialogBox

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogExampleActivity() {
    Column(modifier = Modifier.fillMaxWidth()) {

        var dialogState by remember {
            mutableStateOf(false)
        }
        SystemAlertDialogBox(dialogState) { state ->
            dialogState = !state
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
            Text(text = "Toggle Dialog")
        }
    }
}

@Composable
private fun SystemAlertDialogBox(
    dialogState: Boolean,
    onDismiss: (dialogState: Boolean) -> Unit
) {
    val context = LocalContext.current

    if (dialogState) {
        AlertDialog(
            onDismissRequest = {
                onDismiss(dialogState)
            },
            title = {
                Text(
                    text = "Coding with cat",
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.h6
                )
            },
            text = {
                Text(
                    text = "Subscribe coding with cat is helpful",
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDismiss(dialogState)
                        Toast.makeText(context, "Nice :)", Toast.LENGTH_SHORT).show()
                    }, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Red,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Subscribe", fontSize = 16.sp)
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDismiss(dialogState)
                        Toast.makeText(context, "dismiss -_-", Toast.LENGTH_SHORT).show()
                    }, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.LightGray,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Cancel")
                }
            }
        )
    }


}