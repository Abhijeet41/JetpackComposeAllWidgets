package com.abhi41.jetpackcodingwithcat.examples.widgets.motion_layout

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.ExperimentalUnitApi
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import com.abhi41.jetpackcodingwithcat.R

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MotionLayoutGeekForGeeks() {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Scaffold(topBar = {
            TopAppBar(backgroundColor = Color.Black,
                title = {
                    Text(
                        text = "MotionLayout Button",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                })
        }
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                MotionLayoutButton()
            }
        }
    }

}

@OptIn(ExperimentalMotionApi::class, ExperimentalUnitApi::class)
@Composable
fun MotionLayoutButton() {
    val context = LocalContext.current
    val motionScene = remember {
        context.resources
            .openRawResource(R.raw.motion_geek)
            .readBytes()
            .decodeToString()
    }

    var animateButton by remember { mutableStateOf(false) }
    val buttonAnimationProgress by animateFloatAsState(
        targetValue = if (animateButton) 1f else 0f,
        animationSpec = tween(1000)
    )

    MotionLayout(
        //no need to set constraint here, create json5 in raw folder
        /*ConstraintSet(
            """ {
                  btnKotlin: {
                    width: "spread",
                    height: 120,
                    start: ['parent', 'start', 16],
                    end: ['parent', 'end', 16],
                    top: ['parent', 'top', 0]
                  },
                  btnJava: {
                    width: "spread",
                    height: 120,
                    start: ['parent', 'start', 16],
                    end: ['parent', 'end', 16],
                    top: ['btnKotlin', 'bottom', 16] //top to bottom of button1
                  }
               } """
        ),
        ConstraintSet(
            """ {
                  btnKotlin: {
                    width: 150,
                    height: 120,
                    start: ['parent', 'start', 30],
                    end: ['btnJava', 'start', 10],
                  },
                  btnJava: {
                    width: 150,
                    height: 120,
                    start: ['btnKotlin', 'end', 10],//start to end of btnKotlin
                    end: ['parent', 'end', 30],
                  }
               } """
        ),*/
        motionScene = MotionScene(content = motionScene),
        progress = buttonAnimationProgress,
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
    {
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("btnKotlin")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp),
                    painter = painterResource(id = R.drawable.kotlin),
                    contentDescription = "kotlin"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Kotlin", color = Color.White,
                    fontSize = TextUnit(value = 18F, type = TextUnitType.Sp)
                )
            }
        }
        Button(
            onClick = {
                animateButton = !animateButton
            },
            modifier = Modifier.layoutId("btnJava")
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(3.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .height(60.dp)
                        .width(60.dp),
                    painter = painterResource(id = R.drawable.java),
                    contentDescription = "java"
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    text = "Java", color = Color.White,
                    fontSize = TextUnit(value = 18F, type = TextUnitType.Sp)
                )
            }
        }
    }

}

