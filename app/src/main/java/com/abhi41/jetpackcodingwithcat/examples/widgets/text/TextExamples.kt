package com.abhi41.jetpackcodingwithcat.examples

import android.view.textclassifier.TextSelection
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TextExamples() {
    Column(modifier = Modifier.fillMaxWidth()) {

        TextwithStyle()
        FontText()
        SpaceLine()
        TextOverFlowDemo()
        SpaceLine()
        TextWithSpanRange()
        SpaceLine()
        TextSpanRangeClick()
        SpaceLine()
        TextSelection()
        TextInCenter()
    }

}


@Composable
fun TextwithStyle() {
    Text(
        text = "Coding with Abhi",
        // style = MaterialTheme.typography.body1,
        style = MaterialTheme.typography.h3,
    )
}

@Composable
fun SpaceLine() {
    Spacer(
        modifier = Modifier
            .padding()
            .height(1.dp)
            .fillMaxWidth()
            .background(Color.Black)
    )
}

@Composable
fun FontText() {
    Text(
        text = "Coding with cat",
        style = TextStyle(
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            letterSpacing = 10.sp
        )
    )
}

@Composable
fun TextOverFlowDemo() {
    val context = LocalContext.current
    Text(
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                Toast
                    .makeText(context, "text demo", Toast.LENGTH_LONG)
                    .show()
            },
        text = "Coding with cat Coding with cat Coding with cat Coding with cat Coding with cat Coding with catCoding with cat",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        style = MaterialTheme.typography.body2,
        fontSize = 20.sp,
        textAlign = TextAlign.Center,
        color = Color.Black,
        lineHeight = 40.sp,
    )
}

@Composable
fun TextWithSpanRange() {
    Text(
        buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = Color.Red,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Italic
                )
            ) {
                append("Cat")
                withStyle(
                    style = SpanStyle(
                        color = Color.Blue,
                        fontWeight = FontWeight.ExtraBold,
                    )
                ) {
                    append("SubScribe")
                }
            }

        }
    )
}

@Composable
fun TextSpanRangeClick() {
    val context = LocalContext.current

    val spanRangeClickText = buildAnnotatedString {
        append("Coding with cat, ")
        pushStringAnnotation(
            tag = "tag",
            annotation = "Subscribe coding with cat is very useful"
        )
        withStyle(
            style = SpanStyle(
                color = Color.Red,
                fontWeight = FontWeight.Bold
            )
        ) {
            append("Subscribe")
        }
        pop()
    }
    ClickableText(text = spanRangeClickText, onClick = { offet ->
        spanRangeClickText.getStringAnnotations(
            tag = "tag",
            start = offet,
            end = offet
        ).firstOrNull()?.let { annotation ->
            Toast
                .makeText(context, annotation.item, Toast.LENGTH_LONG)
                .show()
        }
    })

}

@Composable
fun TextSelection() {
    SelectionContainer {
        Text(
            text = "Coding with cat",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TextInCenter() {
    Box(modifier = Modifier
        .size(width = 200.dp, height = 100.dp)
        .background(Color.Cyan)){
        Text(text = "Coding with cat")
    }
}