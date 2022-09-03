package com.abhi41.jetpackcodingwithcat.examples.viewpager

import android.graphics.PorterDuff
import android.widget.RatingBar
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.BottomStart
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.util.lerp
import androidx.compose.ui.viewinterop.AndroidView
import coil.compose.rememberImagePainter
import com.abhi41.jetpackcodingwithcat.examples.viewpager.model.listNatures
import com.abhi41.jetpackcodingwithcat.ui.theme.Purple500
import com.google.accompanist.pager.*
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ViewPagerScreen() {
    val pagerState = rememberPagerState(
        initialPage = 2
    )

    LaunchedEffect(Unit) {
        while (true) {
            delay(2000)
            pagerState.animateScrollToPage(
                page = (pagerState.currentPage + 1) % listNatures.size,
                animationSpec = tween(600)
            )
        }
    }

    Column(modifier = Modifier
        .fillMaxSize()
        .background(Purple500),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center) {

        Text(
            text = "Auto Sliding",
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(30.dp))

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .weight(1f)
                .padding(0.dp, 40.dp, 0.dp, 40.dp),
            count = listNatures.size
        ) { page ->

            Card(modifier = Modifier
                /*   .graphicsLayer {
                       val pageOffset = calculateCurrentOffsetForPage(page).absoluteValue
                       lerp(
                           start = 0.85f,
                           stop = 1f,
                           fraction = 1f - pageOffset
                               .coerceIn(0f, 1f)
                               .also { scale ->
                                   scaleX = scale
                                   scaleY = scale
                               },
                       )
                   }*/
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(15.dp, 0.dp, 15.dp, 0.dp),
                shape = RoundedCornerShape(20.dp)
            ) {
                val natural = listNatures[page]
                val painter = rememberImagePainter(data = natural.imgUri)
                Box(modifier = Modifier
                    .fillMaxSize()
                    .background(Color.LightGray)

                ) {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Crop
                    )
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(BottomCenter)) {
                        Text(
                            text = natural.title,
                            style = MaterialTheme.typography.h5,
                            color = Color.White,
                            fontWeight = FontWeight.Bold
                        )
                        val ratingBar = RatingBar(
                            LocalContext.current, null, android.R.attr.ratingBarStyleSmall
                        ).apply {
                            rating = natural.rating.toFloat()
                            progressDrawable.setColorFilter(
                                android.graphics.Color.parseColor("#FFD700"),
                                PorterDuff.Mode.SRC_ATOP
                            )
                        }
                        AndroidView(
                            factory = { ratingBar },
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                        Text(
                            text = natural.description,
                            style = MaterialTheme.typography.body1,
                            color = Color.White,
                            fontWeight = FontWeight.Normal,
                            modifier = Modifier.padding(0.dp, 8.dp, 0.dp, 0.dp)
                        )
                    }
                }
            }

        }
        HorizontalPagerIndicator(
            pagerState = pagerState,
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally)
        )
    }

}