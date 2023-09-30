package com.projects.statisticshelper.ui.screens.components.table

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.projects.statisticshelper.R
import com.projects.statisticshelper.ui.screens.components.table.model.TableItem
import com.projects.statisticshelper.ui.screens.components.table.model.TableItems
import com.projects.statisticshelper.ui.theme.StatisticshelperTheme
import com.projects.statisticshelper.util.commonBorderForTable

@Composable
fun TextItemTable(
    modifier: Modifier = Modifier,
    text: String,
    onClickPress: (String) -> Unit = {}
) {
    Text(
        modifier = modifier
            .commonBorderForTable()
            .padding(
                dimensionResource(id = R.dimen.dimen_margin_s)
            )
            .clickable {
                onClickPress.invoke(text)
            },
        text = text,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.onPrimary,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun HeaderTable(
    headerTexts: List<String>
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        headerTexts.forEach {
        }
    }
}

@Composable
fun TableGridLayout(
    modifier: Modifier = Modifier,
    tableItems: TableItems
) {
    val context = LocalContext.current
    LazyVerticalGrid(
        modifier = modifier.fillMaxWidth(),
        columns = GridCells.Fixed(tableItems.indexOfLastHeaderItem())
    ) {
        items(
            items = tableItems.tableItems
        ) { item ->
            when (item) {
                is TableItem.Header -> TextItemTable(text = item.name) {
                    Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
                }

                is TableItem.Body -> TextItemTable(text = item.name)
            }
        }
    }
}

@Preview
@Composable
fun TableGridLayoutPreview(
    tableItems: TableItems = TableItems(
        tableItems = listOf(
            TableItem.Header(
                "Classes"
            ),
            TableItem.Header(
                "Frequencia"
            ),
            TableItem.Header(
                "Xi"
            ),
            TableItem.Header(
                "XiFi"
            ),
            TableItem.Body(
                "23"
            ),
            TableItem.Body(
                "5"
            ),
            TableItem.Body(
                "6"
            ),
            TableItem.Body(
                "30"
            )
        )
    )
) {
    StatisticshelperTheme {
        TableGridLayout(tableItems = tableItems)
    }
}

@Preview
@Composable
fun TextItemTablePreview() {
    StatisticshelperTheme {
        TextItemTable(text = "Classes")
    }
}

@Preview
@Composable
fun HeaderTablePreview() {
    StatisticshelperTheme {
        HeaderTable(headerTexts = listOf("Classes", "Frequencia", "Xi"))
    }
}