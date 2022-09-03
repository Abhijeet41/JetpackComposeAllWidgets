package com.abhi41.jetpackcodingwithcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.abhi41.jetpackcodingwithcat.examples.InternetConnection.ConnectionScreen
import com.abhi41.jetpackcodingwithcat.examples.Services.ServicesDemo
import com.abhi41.jetpackcodingwithcat.examples.alarmManager.AlarmManagerDemo
import com.abhi41.jetpackcodingwithcat.examples.coroutine.series.KotlinScopes
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.MainScreen
import com.abhi41.jetpackcodingwithcat.examples.notification.NavGraphs
import com.abhi41.jetpackcodingwithcat.examples.notification.NotificationScreen
import com.abhi41.jetpackcodingwithcat.examples.permission.MutiplePermissions
import com.abhi41.jetpackcodingwithcat.examples.viewpager.ViewPagerScreen
import com.abhi41.jetpackcodingwithcat.examples.widgets.lazy_column.SwipeableLazyColumn
import com.abhi41.jetpackcodingwithcat.examples.widgets.topAppbar.TopAppBarExample
import com.abhi41.jetpackcodingwithcat.ui.theme.JetpackCodingWithCatTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
               // MainScreen()
               // SwipeableLazyColumn()
                DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = rememberNavController(),
                )
                //ViewPagerScreen()
                //ConnectionScreen()
              //  MutiplePermissions()
               // AlarmManagerDemo()
               // KotlinScopes()
               // ServicesDemo()
            }
        }
    }
}
