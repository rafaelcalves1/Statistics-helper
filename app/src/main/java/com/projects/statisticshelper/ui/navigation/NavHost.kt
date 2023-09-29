package com.projects.statisticshelper.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.projects.statisticshelper.model.CalculatorType
import com.projects.statisticshelper.ui.screens.home.HomeScreen
import com.projects.statisticshelper.ui.screens.inputvalors.InputValorsScreen

@Composable
fun StatisticsHelpNavHost(
    navHostController: NavHostController = rememberNavController()
) {
    NavHost(navController = navHostController, startDestination = Routes.Home) {
        composable(
            Routes.InputValors
        ) { backStackEntry ->
            val calculatorTypeString = backStackEntry.arguments?.getString(KEY_INPUT_VALORS)
                ?: CalculatorType.UNKNOWN.value

            val calculatorType = CalculatorType.findByValue(calculatorTypeString)

            InputValorsScreen(calculatorType = calculatorType, onNavigationIconClick = {
                navHostController.popBackStack()
            })
        }

        composable(Routes.Home) {
            HomeScreen(modifier = Modifier.fillMaxSize()) {
                navHostController.navigate(
                    Routes.InputValors.replace(
                        oldValue = KEY_INPUT_VALORS,
                        newValue = it.value
                    )
                )
            }
        }
    }
}