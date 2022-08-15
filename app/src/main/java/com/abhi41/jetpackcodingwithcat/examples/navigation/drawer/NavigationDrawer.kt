package com.abhi41.jetpackcodingwithcat.examples.navigation.drawer

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abhi41.jetpackcodingwithcat.R
import kotlinx.coroutines.launch

@Composable
fun NavigationDrawer() {
    ModalDrawerExample()
}

@Composable
fun ModalDrawerExample() {

    val state = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalDrawer(
        modifier = Modifier.fillMaxSize(),
        scrimColor = Color.Cyan,
        drawerBackgroundColor = Color.White,
        drawerElevation = 5.dp,
        drawerShape = RoundedCornerShape(topEnd = 30.dp),
        gesturesEnabled = true,
        drawerState = state,
        drawerContent = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Image(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .size(150.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit,
                    painter = painterResource(id = R.drawable.abhijeet),
                    contentDescription = ""
                )
                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                Text(text = "Coding with cat", color = Color.DarkGray)

                Spacer(modifier = Modifier.padding(vertical = 20.dp))

                Button(
                    onClick = {
                        scope.launch {
                            state.close()
                        }
                    }
                ) {
                    Text(text = "Subscribe")
                }
            }
        }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.End
        ) {
            Button(
                onClick = {
                    scope.launch {
                        state.open()
                    }
                }
            ) {
                Text(text = "Open")
            }
        }

        BackHandler(
            enabled = state.currentValue == DrawerValue.Open,
            onBack = {
                scope.launch {
                    state.close()
                }
            }
        )

    }

}