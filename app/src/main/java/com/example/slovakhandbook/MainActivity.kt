package com.example.slovakhandbook

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.slovakhandbook.ui.components.NavigationDrawer
import com.example.slovakhandbook.ui.screens.AboutAppScreen
import com.example.slovakhandbook.ui.screens.DetailScreen
import com.example.slovakhandbook.ui.screens.SettingsScreen
import com.example.slovakhandbook.ui.theme.SlovakHandbookTheme
import com.example.slovakhandbook.viewmodels.SettingsViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val sharedPreferences = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val settingsViewModel = SettingsViewModel(sharedPreferences)

            SlovakHandbookTheme(
                darkTheme = true
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "mainScreen"
                ) {
                    composable(route = "mainScreen") {
                        NavigationDrawer(
                            navController = navController,
                            settingsViewModel = settingsViewModel
                        )
                    }
                    composable(route = "settingsScreen") {
                        SettingsScreen(
                            navController = navController,
                            settingsViewModel = settingsViewModel
                        )
                    }
                    composable(route = "aboutAppScreen") {
                        AboutAppScreen(navController = navController)
                    }
                    composable(
                        route = "detailScreen/{imageName}/{description}",
                        arguments = listOf(
                            navArgument("imageName") {
                                type = NavType.StringType
                                defaultValue = "imageName"
                            },
                            navArgument("description") {
                                type = NavType.StringType
                                defaultValue = "description"
                            },
                        )
                    ) {
                        backStackEntry -> DetailScreen(
                            imageName = backStackEntry.arguments?.getString("imageName") ?: "",
                            description = backStackEntry.arguments?.getString("description") ?: "",
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}