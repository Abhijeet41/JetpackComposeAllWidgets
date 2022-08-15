package com.abhi41.jetpackcodingwithcat.examples.widgets.dropdownmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest

@Composable
fun DropDownMenuExample() {
    DropDownMenuWithToggleButton(
        buttonScope = { text, onClick ->
            TextButton(
                onClick = onClick,
                colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Red,
                    contentColor = Color.White
                )
            ) {
                Text(text)
            }
        },
        dropDownMenu = { expanded, dropDownMenuItems, onDropDownMenuItemSelect, onDismissRequest ->
            DropDownDemo(expanded, dropDownMenuItems, onDropDownMenuItemSelect, onDismissRequest)
        }
    )
}


@Composable
fun DropDownMenuWithToggleButton(
    buttonScope: @Composable (text: String, onClick: () -> Unit) -> Unit,
    dropDownMenu: @Composable (
        expanded: Boolean,
        dropDownMenuItems: List<String>,
        onDropDownMenuItemSelect: (selectedIndex: Int) -> Unit,
        onDismissRequest: () -> Unit,
    ) -> Unit,

    ) {

    var expanded by remember {
        mutableStateOf(false)
    }
    val items = listOf("Coding", "Reading", "Swimming", "Dancing", "Playing")

    var selectedIndex by remember {
        //default selected item is 0 index
        mutableStateOf(0)
    }

    Box() {
        //we need a button here
        buttonScope(
            text = items[selectedIndex],
            onClick = {
                //click button to show menu
                expanded = true
            }
        )

        //menu is here
        dropDownMenu(
            expanded = expanded,
            dropDownMenuItems = items,
            onDropDownMenuItemSelect = {
                selectedIndex = it
                expanded = false
            },
            onDismissRequest = {
                expanded = false
            }
        )
    }

}


@Composable
fun DropDownDemo(
    expanded: Boolean,
    dropDownMenuItems: List<String>,
    onDropDownMenuItemSelect: (selectedIndex: Int) -> Unit,
    onDismissRequest: () -> Unit,
) {

    DropdownMenu(
        modifier = Modifier
            .wrapContentSize()
            .background(Color.LightGray)
            .padding(20.dp),
        offset = DpOffset(0.dp, 20.dp),
        expanded = expanded,
        onDismissRequest = {

        },

        ) {
        // a string list we need
        dropDownMenuItems.forEachIndexed { index, text ->
            DropdownMenuItem(onClick = {
                onDropDownMenuItemSelect(index)
            }) {

                //if index is 3 then 4th item, we want to show a image and a text.

                if (index == 3) {
                    Image(
                        modifier = Modifier.size(30.dp),
                        painter = rememberAsyncImagePainter(
                            model = ImageRequest.Builder(LocalContext.current)
                                .crossfade(true)
                                .data("https://developer.android.com/images/brand/Android_Robot.png")
                                .build()
                        ),
                        contentDescription = null
                    )
                }

                Text(text = text, color = Color.DarkGray)
            }
        }
    }
}

