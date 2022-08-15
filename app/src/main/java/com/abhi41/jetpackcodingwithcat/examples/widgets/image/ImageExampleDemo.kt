package com.abhi41.jetpackcodingwithcat.examples.widgets.image

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.abhi41.jetpackcodingwithcat.R

@Composable
fun ImageExampleDemo() {

    Column(modifier = Modifier.fillMaxWidth()) {
        ImageResoure()
        Spacer(modifier = Modifier.height(10.dp))
        ShapeImage()
        Spacer(modifier = Modifier.height(10.dp))
        ImageUrl()
        Spacer(modifier = Modifier.height(10.dp))
        ImageSurfaceShape()
    }
}

@Composable
fun ImageResoure() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.abhijeet),
            contentDescription = ""
        )
        Spacer(modifier = Modifier.fillMaxWidth())
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
                    .background(Color.Cyan),
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = "",
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.padding(vertical = 3.dp))
            Image(
                modifier = Modifier
                    .size(width = 120.dp, height = 120.dp)
                    .background(Color.Cyan),
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}

@Composable
fun ShapeImage() {
    val context = LocalContext.current

    Image(
        painter = painterResource(id = R.drawable.abhijeet),
        contentDescription = null,
        contentScale = ContentScale.Fit,
        modifier = Modifier
            .size(130.dp)
            .clip(CircleShape)
            .clickable {
                Toast
                    .makeText(context, "Subscribe ~~~~", Toast.LENGTH_SHORT)
                    .show()
            }
    )

}

@Composable
fun ImageUrl() {
    Image(
        painter = rememberImagePainter(
            data = "https://developer.android.com/images/brand/Android_Robot.png"
        ),
        contentDescription = "Android Logo",
        modifier = Modifier.size(150.dp)
    )
}

@Composable
fun ImageSurfaceShape() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Surface(
            shape = CircleShape,
            color = Color.Cyan
        ) {
            Image(
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.size(120.dp)
            )
        }

        Spacer(modifier = Modifier.padding(horizontal = 3.dp))

        androidx.compose.material.Surface(
            shape = CircleShape,
            border = BorderStroke(5.dp, color = Color.LightGray)
        ) {
            Image(
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(120.dp)
            )
        }
    }
}
