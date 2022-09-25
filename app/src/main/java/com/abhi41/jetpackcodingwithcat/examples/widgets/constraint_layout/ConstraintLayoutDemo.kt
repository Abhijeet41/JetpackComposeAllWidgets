package com.abhi41.jetpackcodingwithcat.examples.widgets.constraint_layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension


@Composable
fun ConstraintLayoutDemo() {

    val constraints = ConstraintSet {
        val greenBox = createRefFor("greenbox")
        val redBox = createRefFor("redbox")
        val guideline = createGuidelineFromTop(fraction = 0.2f)

        constrain(greenBox) {
            // top.linkTo(parent.top)
            top.linkTo(guideline) //this working as a vertical bias in xml
            start.linkTo(parent.start)
            //width = Dimension.fillToConstraints //refers 0dp in xml
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }

        constrain(redBox) {
            top.linkTo(greenBox.bottom)//top to bottom of green box
            start.linkTo(parent.start)//start to start of parent
            start.linkTo(greenBox.end)//start to end of green box
            width = Dimension.value(100.dp)
            height = Dimension.value(100.dp)
        }
        //this worked as chain we used to work in xml
        createHorizontalChain(greenBox, redBox, chainStyle = ChainStyle.Packed)

    }
    Column(modifier = Modifier.fillMaxSize()) {
        ConstraintLayout(
            modifier = Modifier.fillMaxWidth(),
            constraintSet = constraints
        ) {
            Box(
                modifier = Modifier
                    .padding(bottom = 10.dp, top = 10.dp, start = 10.dp)
                    .background(Color.Green)
                    .layoutId("greenbox")
            )
            //Spacer(modifier = Modifier.height(30.dp)) this won't work
            Box(
                modifier = Modifier
                    .padding(start = 10.dp)
                    .background(Color.Red)
                    .layoutId("redbox")
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            var name by remember {
                mutableStateOf("")
            }
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = name,
                onValueChange = {
                    name = it
                },
                label = {},
                placeholder = {
                    Text(text = "your email")
                }
            )
        }

    }


}

@Preview
@Composable
fun ConstraintPreview() {
    ConstraintLayoutDemo()
}