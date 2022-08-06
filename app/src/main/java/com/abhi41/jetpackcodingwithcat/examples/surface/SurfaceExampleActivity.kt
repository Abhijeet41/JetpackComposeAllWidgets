package com.abhi41.jetpackcodingwithcat.examples.surface

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.abhi41.jetpackcodingwithcat.R

@Composable
fun SurfaceExampleActivity() {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        SurfaceExample1()
        Spacer(modifier = Modifier.padding(vertical = 5.dp))
        SurfaceExample2()
    }
}

@Composable
fun SurfaceExample1() {
    val context = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .clickable(
                    interactionSource = CreateMutableIneractionSource(),
                    indication = CreateIndication(color = Color.Red),
                    onClick = {
                        Toast
                            .makeText(context, "Surface example 1", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                .padding(15.dp)
        ) {
            Text(
                buildAnnotatedString {
                    append("coding with Abhi")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                    ) {
                        append("is good")
                    }
                }
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))
            Text(buildAnnotatedString {
                append("Have you subscribe to my channel?")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("\nNot yet? -_-......")
                }
            })
        }
    }

}

@Composable
fun SurfaceExample2() {
    val context = LocalContext.current
    Surface(
        shape = RoundedCornerShape(8.dp),
        elevation = 5.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .clickable(
                    interactionSource = CreateMutableIneractionSource(),
                    indication = CreateIndication(),
                    onClick = {
                        Toast
                            .makeText(context, "Surface example 2", Toast.LENGTH_SHORT)
                            .show()
                    }
                )
                .padding(15.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .clip(CircleShape)
                    .size(130.dp),
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = "",
                contentScale = ContentScale.Fit,
            )

            Spacer(modifier = Modifier.padding(10.dp))

            Column(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()) {
                Text(
                    buildAnnotatedString {
                        append("coding with Abhi")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)
                        ) {
                            append("is good")
                        }
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 5.dp))
                Text(buildAnnotatedString {
                    append("Have you subscribe to my channel?")
                    withStyle(
                        style = SpanStyle(fontWeight = FontWeight.Bold)
                    ) {
                        append("\nNot yet? -_-......")
                    }
                })
            }
        }
    }
}

@Composable
private fun CreateMutableIneractionSource(): MutableInteractionSource = remember {
    MutableInteractionSource()
}

@Composable
fun CreateIndication(bounded: Boolean = true, color: Color = Color.Gray) =
    rememberRipple(bounded = bounded, color = color)
