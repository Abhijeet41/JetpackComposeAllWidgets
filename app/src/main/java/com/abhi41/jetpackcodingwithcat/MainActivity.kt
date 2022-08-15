package com.abhi41.jetpackcodingwithcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.MainScreen
import com.abhi41.jetpackcodingwithcat.examples.widgets.topAppbar.TopAppBarExample
import com.abhi41.jetpackcodingwithcat.ui.theme.JetpackCodingWithCatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackCodingWithCatTheme {
                //TextExamples()
                //ButtonExample()
                //ImageExampleDemo()
                //CardExampleActivity()
                //SurfaceExampleActivity()
                //TextFieldActivity()
                //CustomTextFieldActivity()
               //BoxActivity()
               // AlertDialogExampleActivity()
               // CustomAlertDialogExampleActivity()
               // ModalBottomSheetActivity()
               // NavigationDrawer()
               // TopAppBarExample()
                MainScreen()
            }
        }
    }
}
