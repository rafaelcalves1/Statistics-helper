package com.projects.statisticshelper.util

import androidx.annotation.DimenRes
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.projects.statisticshelper.R

@Composable
@ReadOnlyComposable
fun fontDimensionResource(@DimenRes id: Int) = dimensionResource(id = id).value.sp


@Composable
fun Modifier.commonBorderForInput() = this.border(
    2.dp,
    color = MaterialTheme.colorScheme.primary,
    shape = RoundedCornerShape(
        dimensionResource(id = R.dimen.dimen_margin_xxs)
    )
)
