package com.abhi41.jetpackcodingwithcat.examples.button

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ButtonExample() {
    Column(modifier = Modifier.fillMaxWidth()) {
        ColorButton()
        Spacer(modifier = Modifier.height(20.dp))
        IconTextButton()
        Spacer(modifier = Modifier.height(20.dp))
        ButtonBorderShape()
    }
}


@Composable
fun ColorButton() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {

        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            onClick = {
                Toast.makeText(context, "click just button", Toast.LENGTH_SHORT).show()
            }) {
            Text(text = "Button", color = Color.White)
        }

    }

}

@Composable
fun IconTextButton() {
    val context = LocalContext.current

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red),
            onClick = {
                Toast.makeText(context, "Click Button", Toast.LENGTH_LONG).show()
            }
        ) {
            Icon(
                modifier = Modifier.size(ButtonDefaults.IconSize),
                imageVector = Icons.Filled.Email,
                contentDescription = "email icon",
                tint = Color.White
            )
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSize))
            Text(text = "button", color = Color.White)
        }
    }

}

@Composable
fun ButtonBorderShape() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Button(
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
            border = BorderStroke(2.dp, Color.Red),
            shape = RoundedCornerShape(100.dp),
            elevation = ButtonDefaults.elevation(10.dp),
            onClick = {

            }
        ) {
            Text(text = "Button", color = Color.White)
        }
    }

}


