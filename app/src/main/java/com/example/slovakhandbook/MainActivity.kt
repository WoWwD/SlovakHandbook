package com.example.slovakhandbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.slovakhandbook.screens.DetailScreen
import com.example.slovakhandbook.screens.MainScreen
import com.example.slovakhandbook.ui.theme.SlovakHandbookTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            SlovakHandbookTheme(
                darkTheme = true
            ) {
                NavHost(
                    navController = navController,
                    startDestination = "mainScreen"
                ) {
                    composable(route = "mainScreen") { MainScreen( navController = navController) }
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