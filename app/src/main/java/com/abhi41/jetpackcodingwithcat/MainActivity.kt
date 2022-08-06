package com.abhi41.jetpackcodingwithcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.abhi41.jetpackcodingwithcat.examples.TextExamples
import com.abhi41.jetpackcodingwithcat.examples.bottomsheet.ModalBottomSheetActivity
import com.abhi41.jetpackcodingwithcat.examples.box.BoxActivity
import com.abhi41.jetpackcodingwithcat.examples.button.ButtonExample
import com.abhi41.jetpackcodingwithcat.examples.card.CardExampleActivity
import com.abhi41.jetpackcodingwithcat.examples.dialogBox.AlertDialogExampleActivity
import com.abhi41.jetpackcodingwithcat.examples.dialogBox.CustomAlertDialogExampleActivity
import com.abhi41.jetpackcodingwithcat.examples.image.ImageExampleDemo
import com.abhi41.jetpackcodingwithcat.examples.surface.SurfaceExampleActivity
import com.abhi41.jetpackcodingwithcat.examples.textfield.CustomTextFieldActivity
import com.abhi41.jetpackcodingwithcat.examples.textfield.TextFieldActivity
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
                ModalBottomSheetActivity()
            }
        }
    }
}
