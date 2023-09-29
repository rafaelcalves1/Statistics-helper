package com.projects.statisticshelper.ui.screens.inputvalors

import androidx.compose.foundation.text.KeyboardOptions
import com.projects.statisticshelper.model.CalculatorType

data class InputValorsViewState(
    val isClearTextVisible: Boolean = false,
    val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    val isError: Boolean = false,
    val calculatorType: CalculatorType = CalculatorType.UNKNOWN
)