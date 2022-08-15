package com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.screen

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.navigation.BottomBarScreen
import com.abhi41.jetpackcodingwithcat.examples.navigation.bottom_navigation.navigation.BottomNavGraph

@Composable
fun MainScreen() {
    val navcontroller = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomBar(navController = navcontroller)
        }
    ) {
        BottomNavGraph(navController = navcontroller)
    }

}


@Composable
fun BottomBar(
    navController: NavHostController,
) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Setting,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation() {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(painterResource(id = screen.icon), contentDescription = "")
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route){
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true //to avoid multiple copies of same destination
            }
        },
        unselectedContentColor = LocalContentColor.current.copy(ContentAlpha.disabled),
    )
}