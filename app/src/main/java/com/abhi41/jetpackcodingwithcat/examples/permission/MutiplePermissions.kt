package com.abhi41.jetpackcodingwithcat.examples.permission

import android.Manifest
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MutiplePermissions() {

    val permissionState = rememberMultiplePermissionsState(
        permissions = listOf(
            Manifest.permission.CAMERA,
            Manifest.permission.READ_CONTACTS
        )
    )

    val lifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(key1 = lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                permissionState.launchMultiplePermissionRequest()
                /*
                so basically we are checking permission in resume because
                when user minimize the app and disable permission from setting
                then we need to re-authenticate
                */

            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        permissionState.permissions.forEach { perm ->
            when (perm.permission) {
                Manifest.permission.CAMERA -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "Camerta Permission accepted")
                        }
                        perm.shouldShowRationale -> {
                            Text(text = "Camerta Permission is needed " +
                                    "to access the camera")
                        }
                        perm.isPermanentlyDenied() -> {
                            Text(text = "Camerta Permission was denied " +
                                    "permanently access it from setting")
                        }
                    }
                }
                Manifest.permission.READ_CONTACTS -> {
                    when {
                        perm.hasPermission -> {
                            Text(text = "ReadContact Permission accepted")
                        }
                        perm.shouldShowRationale -> {
                            Text(text = "ReadContact Permission is needed " +
                                    "to access the contact")
                        }
                        perm.isPermanentlyDenied() -> {
                            Text(text = "ReadContact Permission was denied " +
                                    "permanently access it from setting")
                        }
                    }
                }
            }
        }

    }

}