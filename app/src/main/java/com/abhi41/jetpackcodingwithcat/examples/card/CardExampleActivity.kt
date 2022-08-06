package com.abhi41.jetpackcodingwithcat.examples.card

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
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
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.abhi41.jetpackcodingwithcat.R

@Composable
fun CardExampleActivity() {
    Column(modifier = Modifier.fillMaxWidth()){
        CardExampleWithText()
        CardExampleWithImage()
        CardWithTextAndImage()
    }
}

@Composable
fun CardExampleWithText() {

    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                /*
                  this is custom ripple effect
               */
                interactionSource = CreateMutableInteractionSource(),
                indication = CreateIndication(color = Color.Red),
                onClick = {
                    Toast
                        .makeText(context, "Card example 1", Toast.LENGTH_LONG)
                        .show()
                }
            ),
        backgroundColor = Color.White,
        elevation = 8.dp
    ) {
        Text(
            modifier = Modifier.padding(16.dp),
            text = "Coding with cat"
        )
    }

}

@Composable
fun CardExampleWithImage() {
    val context = LocalContext.current

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                /*
                  this is custom ripple effect
               */
                interactionSource = CreateMutableInteractionSource(),
                indication = CreateIndication(color = Color.Red),
                onClick = {
                    Toast
                        .makeText(context, "Card example 1", Toast.LENGTH_LONG)
                        .show()
                }
            ),
        backgroundColor = Color.Cyan,
        elevation = 8.dp
    ) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Image(painterResource(id = R.drawable.ic_launcher_background), contentDescription = "")
        }
    }
}

@Composable
fun CardWithTextAndImage() {
    val context = LocalContext.current
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable(
                /*
                  this is custom ripple effect
               */
                interactionSource = CreateMutableInteractionSource(),
                indication = CreateIndication(color = Color.Black),
                onClick = {
                    Toast
                        .makeText(context, "Card Text with Image", Toast.LENGTH_LONG)
                        .show()
                }
            ),
        backgroundColor = Color.LightGray,
        elevation = 8.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier
                    .size(70.dp)
                    .clip(CircleShape),
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    buildAnnotatedString {
                        append("Coding with ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = Color.Red
                            )
                        ){
                            append("Abhijeet")
                        }
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                    }
                )
                Spacer(modifier = Modifier.padding(vertical = 2.dp))
                Text(
                    buildAnnotatedString {
                        append("Android development")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Italic,
                                color = Color.Red
                            )
                        ){
                            append("Tutorial")
                        }
                        Spacer(modifier = Modifier.padding(vertical = 2.dp))
                    }
                )
            }
        }
    }
}


@Composable
fun CreateMutableInteractionSource(): MutableInteractionSource = remember {
    MutableInteractionSource()
}

@Composable
fun CreateIndication(
    bounded: Boolean = true,
    color: Color
) = rememberRipple(bounded = bounded, color = color)