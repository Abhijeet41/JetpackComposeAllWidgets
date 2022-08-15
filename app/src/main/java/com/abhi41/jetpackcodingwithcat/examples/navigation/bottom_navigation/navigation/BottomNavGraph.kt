package com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.HomeScreen
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.ProfileScreen
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen.SettingScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {
    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Home.route
    ){
        composable(route = BottomBarScreen.Home.route){
            HomeScreen()
        }
        composable(route = BottomBarScreen.Profile.route){
            ProfileScreen()
        }
        composable(route = BottomBarScreen.Setting.route){
            SettingScreen()
        }
    }

}