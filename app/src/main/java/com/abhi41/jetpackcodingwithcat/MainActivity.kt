package com.abhi41.jetpackcodingwithcat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.abhi41.jetpackcodingwithcat.examples.InternetConnection.ConnectionScreen
import com.abhi41.jetpackcodingwithcat.examples.Services.ServicesDemo
import com.abhi41.jetpackcodingwithcat.examples.Shared_pref_security.SharedPrefScreen
import com.abhi41.jetpackcodingwithcat.examples.TracklocationBackground.TrackLocationScreen
import com.abhi41.jetpackcodingwithcat.examples.alarmManager.AlarmManagerDemo
import com.abhi41.jetpackcodingwithcat.examples.broad_cast_receiver.BoradCastScreen
import com.abhi41.jetpackcodingwithcat.examples.coroutine.channels.ChannelsExample
import com.abhi41.jetpackcodingwithcat.examples.coroutine.series.KotlinScopes
import com.abhi41.jetpackcodingwithcat.examples.crytptography.CryptographyScreen
import com.abhi41.jetpackcodingwithcat.examples.double_back_exit.DoubleBackToExit
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.MainScreen
import com.abhi41.jetpackcodingwithcat.examples.notification.NotificationScreen
import com.abhi41.jetpackcodingwithcat.examples.permission.MutiplePermissions
import com.abhi41.jetpackcodingwithcat.examples.viewpager.ViewPagerScreen
import com.abhi41.jetpackcodingwithcat.examples.widgets.animation.BorderAnimation
import com.abhi41.jetpackcodingwithcat.examples.widgets.animation.InfiniteAnimation
import com.abhi41.jetpackcodingwithcat.examples.widgets.collapsing_effect.CollapsingEffectScreen
import com.abhi41.jetpackcodingwithcat.examples.widgets.constraint_layout.ConstraintLayoutDemo
import com.abhi41.jetpackcodingwithcat.examples.widgets.date_time_picker.DateAndTimePicker
import com.abhi41.jetpackcodingwithcat.examples.widgets.lazy_column.SwipeableLazyColumn
import com.abhi41.jetpackcodingwithcat.examples.widgets.motion_layout.CollapsingAppbar
import com.abhi41.jetpackcodingwithcat.examples.widgets.motion_layout.MotionLayoutGeekForGeeks
import com.abhi41.jetpackcodingwithcat.examples.widgets.multi_preview.MultiPreview
import com.abhi41.jetpackcodingwithcat.examples.widgets.slider.SliderExample
import com.abhi41.jetpackcodingwithcat.examples.widgets.textfield.CustomTextFieldActivity
import com.abhi41.jetpackcodingwithcat.examples.widgets.textfield.TextFieldActivity
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
               // TextFieldActivity()
               // CustomTextFieldActivity()
               //BoxActivity()
               // AlertDialogExampleActivity()
               // DateAndTimePicker()
               // CustomAlertDialogExampleActivity()
               // ModalBottomSheetActivity()
               // NavigationDrawer()
               // TopAppBarExample()
               // MainScreen()
               // SwipeableLazyColumn()
                /*DestinationsNavHost(
                    navGraph = NavGraphs.root,
                    navController = rememberNavController(),
                )*/
                //ViewPagerScreen()
                //ConnectionScreen()
              //  MutiplePermissions()
               // AlarmManagerDemo()
               // KotlinScopes()
               // ServicesDemo()
               // CryptographyScreen()
               // SharedPrefScreen()
              //  CollapsingEffectScreen()
               // DoubleBackToExit()
               // ConstraintLayoutDemo()
               // MultiPreview()
               // MotionLayoutGeekForGeeks()
               // CollapsingAppbar()
               // TrackLocationScreen()
                //BorderAnimation()
                //InfiniteAnimation()
               // SliderExample()
                //ChannelsExample()
                BoradCastScreen()
            }
        }
    }
}
