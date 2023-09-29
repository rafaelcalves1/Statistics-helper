package com.projects.statisticshelper

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.projects.statisticshelper.model.CalculatorType
import com.projects.statisticshelper.model.HomeItemsModel
import com.projects.statisticshelper.ui.navigation.StatisticsHelpNavHost
import com.projects.statisticshelper.ui.theme.StatisticshelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            StatisticshelperTheme {
                StatisticsHelpNavHost()
            }
        }
    }

    companion object {
        val HOME_ITEMS_MODEL = listOf(
            HomeItemsModel(
                text = "Dados Continuos",
                calculatorType = CalculatorType.CONTINUOUS_DATA
            ),
            HomeItemsModel(
                text = "Dados Discretos",
                calculatorType = CalculatorType.DISCRETE_DATA
            ),
            HomeItemsModel(
                text = "Dados Discretos Agrupados",
                CalculatorType.UNGROUPED_DISCRETE_DATA
            )
        )
    }
}