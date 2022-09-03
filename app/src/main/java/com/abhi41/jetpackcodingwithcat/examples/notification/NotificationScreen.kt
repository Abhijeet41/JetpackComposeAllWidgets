package com.abhi41.jetpackcodingwithcat.examples.notification

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.abhi41.jetpackcodingwithcat.examples.notification.destinations.DetailScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(start = true)
@Composable
fun NotificationScreen(
    navigator: DestinationsNavigator?,
) {
    val notificationViewModel: NotificationViewModel = hiltViewModel()
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Button(
            onClick = {
                notificationViewModel.showSimpleNotification()
            }
        ) {
            Text(text = "Simple Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                notificationViewModel.showReplyNotification()
            }
        ) {
            Text(text = "Reply Message Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                notificationViewModel.updateSimpleNotification(
                    title = "NEW TITLE",
                    description = "new Description"
                )
            }
        ) {
            Text(text = "Update Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                notificationViewModel.showDownloadProgressNotification()
            }
        ) {
            Text(text = "Download Progress Notification")
        }

        Spacer(modifier = Modifier.height(12.dp))
        Button(
            onClick = {
                notificationViewModel.cancelSimpleNotification()
            }
        ) {
            Text(text = "Cancel Notification")
        }
        Spacer(modifier = Modifier.height(12.dp))
        Button(onClick = {
            navigator?.navigate(DetailScreenDestination(
                message = "hi there, coming from notification screen...",
            ))
        }) {
            Text(text = "Details Screen")
        }
    }
}

@Preview
@Composable
fun NotificationScreenPreview() {
    NotificationScreen(
        navigator = null,
    )
}