package com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.navigation

import androidx.annotation.DrawableRes
import com.abhi41.jetpackcodingwithcat.R

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    @DrawableRes val icon: Int
){
    object Home: BottomBarScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home_24
    )

    object Profile: BottomBarScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_profile_24
    )
    object Setting: BottomBarScreen(
        route = "setting",
        title = "Setting",
        icon = R.drawable.ic_settings_24
    )
}
