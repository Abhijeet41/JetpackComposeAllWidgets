@file:OptIn(ExperimentalMaterialApi::class)

package com.abhi41.jetpackcodingwithcat.examples.bottomsheet

import android.content.Context
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.abhi41.jetpackcodingwithcat.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun ModalBottomSheetActivity() {
    BottomActionSheetWithContent { state, scope ->
        //Make ModalBottomSheetLayout to wrap the content

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.abhijeet),
                contentDescription = null, modifier = Modifier
                    .padding(top = 16.dp)
                    .size(200.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Fit
            )
            Spacer(modifier = Modifier.padding(horizontal = 10.dp))
            Button(
                onClick = {
                    scope.launch {
                        //state.show()
                        //or
                        state.animateTo(
                            ModalBottomSheetValue.Expanded,
                            tween(500)
                        )
                    }
                }
            ) {
                Text(text = "Toggle Action SHeet")
            }
            BackHandler(
                enabled = (state.currentValue == ModalBottomSheetValue.HalfExpanded ||
                        state.currentValue == ModalBottomSheetValue.Expanded),
                onBack = {
                    scope.launch {
                        // state.hide()
                        //or
                        state.animateTo(ModalBottomSheetValue.Hidden, tween(300))
                    }
                }
            )
        }
    }
}

@Composable
fun BottomActionSheetWithContent(
    activityContentScope: @Composable (state: ModalBottomSheetState, scope: CoroutineScope) -> Unit
) {
    val state = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()

    val context = LocalContext.current

    ModalBottomSheetLayout(
        sheetBackgroundColor = Color.White,
        sheetElevation = 5.dp,
        sheetShape = RoundedCornerShape(topStart = 30.dp),
        sheetState = state,
        sheetContent = {
            //action sheet title header part
            Surface(color = Color.DarkGray) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    contentAlignment = Alignment.CenterEnd
                ) {
                    Text(
                        modifier = Modifier.padding(end = 10.dp),
                        text = "Bottom Sheet",
                        color = Color.White
                    )

                }
            }
            //--WARNNING-- bottomsheet content always show below surface
            Spacer(modifier = Modifier.padding(vertical = 16.dp))

            ActionSheetItem(
                context = context,
                imageResource = R.drawable.abhijeet,
                text = "Coding"
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            ActionSheetItem(
                context = context,
                imageResource = R.drawable.abhijeet,
                text = "With"
            )
            Spacer(modifier = Modifier.padding(vertical = 5.dp))

            ActionSheetItem(
                context = context,
                imageResource = R.drawable.abhijeet,
                text = "Cat"
            )
        }
    ) {
        activityContentScope(state, scope)
    }
}

@Composable
private fun ActionSheetItem(
    context: Context,
    imageResource: Int,
    text: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable {
                Toast
                    .makeText(context, text, Toast.LENGTH_SHORT)
                    .show()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null, modifier = Modifier
                .padding(4.dp)
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.padding(horizontal = 10.dp))
        Text(text = text)


    }
}