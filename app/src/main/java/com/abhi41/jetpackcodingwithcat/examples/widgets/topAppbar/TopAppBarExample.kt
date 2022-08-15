package com.abhi41.jetpackcodingwithcat.examples.widgets.topAppbar

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.abhi41.jetpackcodingwithcat.R

@Composable
fun TopAppBarExample() {
    val context = LocalContext.current
    TopAppbarDemo(
        onImageClick = {
            Toast.makeText(context, "should Image CLick", Toast.LENGTH_SHORT).show()
        },
        onBackClick = {
            Toast.makeText(context, "should finished", Toast.LENGTH_SHORT).show()
        }
    )
}

@Composable
fun TopAppbarDemo(
    onBackClick: () -> Unit,
    onImageClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        TopAppBar(
            elevation = 4.dp,
            title = {
                Text(
                    text = "Coding with abhi",
                    color = Color.White,
                    fontSize = 15.sp
                )
            },
            backgroundColor = Color.DarkGray,
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "",
                        tint = Color.White
                    )
                }
            },
            actions = {
                IconButton(onClick = { }) {
                    Icon(
                        imageVector = Icons.Filled.Share,
                        contentDescription = null,
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                Image(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(vertical = 10.dp)
                        .clip(CircleShape)
                        .clickable {
                            onImageClick
                        },
                    painter = painterResource(id = R.drawable.abhijeet),
                    contentDescription = "",
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.padding(horizontal = 3.dp))
                IconButton(onClick = { }) {
                    Icon(imageVector = Icons.Filled.Settings, contentDescription = null)
                }
            }
        )
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "App Top Bar Example",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}