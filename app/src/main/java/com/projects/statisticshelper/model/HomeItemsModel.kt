package com.projects.statisticshelper.model

import android.os.Parcelable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AlignHorizontalLeft
import androidx.compose.material.icons.filled.AlignHorizontalRight
import androidx.compose.material.icons.filled.AlignVerticalBottom
import androidx.compose.material.icons.filled.ErrorOutline
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import kotlinx.parcelize.Parcelize
import kotlin.random.Random

data class HomeItemsModel(
    val text: String,
    val calculatorType: CalculatorType,
    val id: Int = (Random.nextInt() + 1)
)

@Parcelize
enum class CalculatorType(val value: String): Parcelable {
    CONTINUOUS_DATA("CONTINUOUS_DATA"),
    DISCRETE_DATA("DISCRETE_DATA"),
    UNGROUPED_DISCRETE_DATA("UNGROUPED_DISCRETE_DATA"),
    UNKNOWN("UNKNOWN");

    companion object {
        fun findByValue(value: String): CalculatorType {
            return if(value.isNullOrBlank()) UNKNOWN else
                values().find { it.value.equals(value, ignoreCase = true) } ?: UNKNOWN
        }
    }
}

fun CalculatorType.setIcon() = when (this) {
    CalculatorType.CONTINUOUS_DATA -> Icons.Filled.AlignHorizontalLeft
    CalculatorType.DISCRETE_DATA -> Icons.Filled.AlignHorizontalRight
    CalculatorType.UNGROUPED_DISCRETE_DATA -> Icons.Filled.AlignVerticalBottom
    else -> Icons.Filled.ErrorOutline
}

fun CalculatorType.getKeyboardType(): KeyboardOptions {
    return when (this) {
        CalculatorType.CONTINUOUS_DATA -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        CalculatorType.UNGROUPED_DISCRETE_DATA -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        CalculatorType.DISCRETE_DATA -> KeyboardOptions(keyboardType = KeyboardType.Decimal)
        CalculatorType.UNKNOWN -> KeyboardOptions.Default
    }
}
