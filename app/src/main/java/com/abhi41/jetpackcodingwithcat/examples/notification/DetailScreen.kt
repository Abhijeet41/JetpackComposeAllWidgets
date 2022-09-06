package com.abhi41.jetpackcodingwithcat.examples.notification

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.navArgument
import com.abhi41.jetpackcodingwithcat.util.Constants.DETAIL_SCREEN
import com.abhi41.jetpackcodingwithcat.util.Constants.MY_ARG
import com.abhi41.jetpackcodingwithcat.util.Constants.MY_URI
import com.ramcosta.composedestinations.annotation.DeepLink
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

//official doc from rafel
@Destination(
    route = "detail_screen",
    deepLinks = [
        DeepLink(
            uriPattern = "{$MY_URI}${DETAIL_SCREEN}/{message}"
        )
    ]
)
//this is alternative with out route parameter
/*@Destination(
    deepLinks = [
        DeepLink(
            uriPattern = "{$MY_URI}/{message}"
        )
    ]
)*/
@Composable
fun DetailScreen(
    message: String,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = message,
            style = TextStyle(
                fontSize = MaterialTheme.typography.h6.fontSize,
                fontWeight = FontWeight.Bold
            )
        )
    }
}