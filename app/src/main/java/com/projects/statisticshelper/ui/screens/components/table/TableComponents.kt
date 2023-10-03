package com.projects.statisticshelper.ui.screens.components.table

import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.projects.statisticshelper.R
import com.projects.statisticshelper.ui.theme.StatisticshelperTheme
import com.projects.statisticshelper.util.commonBorderForTable
import com.projects.statisticshelper.util.setWidht

@Composable
fun TextItemTable(
    modifier: Modifier = Modifier,
    text: String,
    widthItem: Dp? = null,
    onWidthSet: (Dp) -> Unit = {},
    onClickPress: (String) -> Unit = {}
) {
    val localDensity = LocalDensity.current

    Text(
        modifier = modifier
            .commonBorderForTable()
            .padding(
                dimensionResource(id = R.dimen.dimen_margin_s)
            )
            .setWidht(widthItem)
            .onGloballyPositioned { coordinates ->
                with(localDensity) {
                    onWidthSet.invoke(
                        coordinates.size.width.toDp()
                    )
                }
            }
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

@Composable
fun Table(
    modifier: Modifier = Modifier,
    headerList: List<String>,
    bodyList: List<List<String>>
) {
    var maxWidthValueHeader by remember {
        mutableStateOf<Dp?>(null)
    }
    var maxWidthValueBody by remember {
        mutableStateOf<Dp?>(null)
    }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        RowTable(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
            list = headerList,
            witdhItem = maxWidthValueHeader,
            onWidthSet = { headerWidthdp ->
                if (maxWidthValueHeader == null) {
                    maxWidthValueHeader = headerWidthdp
                    return@RowTable
                }

                maxWidthValueHeader?.let { currentHeaderDp ->
                    if (currentHeaderDp < headerWidthdp) maxWidthValueHeader = headerWidthdp
                }
            }
        )
        Column(
            modifier = Modifier.horizontalScroll(rememberScrollState()),
        ) {
            bodyList.forEach {
                RowTable(
                    list = it,
                    witdhItem = maxWidthValueBody,
                    onWidthSet = { bodyWidthdp ->

                        if (maxWidthValueBody == null) {
                            maxWidthValueBody = bodyWidthdp
                            return@RowTable
                        }

                        maxWidthValueBody?.let { currentBodyDp ->
                            if (currentBodyDp < bodyWidthdp) maxWidthValueBody = bodyWidthdp

                            maxWidthValueHeader?.let { currentHeaderDp ->

                                if (maxWidthValueBody!! > currentHeaderDp) {
                                    maxWidthValueHeader = maxWidthValueBody
                                    return@let
                                }
                                maxWidthValueBody = currentHeaderDp
                            }
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun RowTable(
    modifier: Modifier = Modifier,
    list: List<String>,
    witdhItem: Dp? = null,
    onWidthSet: (Dp) -> Unit = { }
) {

    Row(
        modifier = modifier
    ) {
        for (i in list) {
            TextItemTable(
                text = i,
                widthItem = witdhItem,
                onWidthSet = { dp ->
                    onWidthSet.invoke(dp)
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RowTablePreview() {
    StatisticshelperTheme {
        RowTable(
            list = listOf(
                stringResource(id = R.string.table_componenst_header_value_one),
                stringResource(id = R.string.table_componenst_header_value_two),
                stringResource(id = R.string.table_componenst_header_value_three),
                stringResource(id = R.string.table_componenst_header_value_four),
                stringResource(id = R.string.table_componenst_header_value_five)
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TablePreview() {
    val list1 = listOf("55", "5", "2", "5", "10")
    val list2 = listOf("56", "6", "3", "11", "18")
    val list3 = listOf("57", "7", "4", "18", "28")
    val list4 = listOf("58", "8", "5", "26", "40")

    val bodyList = listOf(list1, list2, list3, list4)

    StatisticshelperTheme {
        Table(
            headerList = listOf(
                stringResource(id = R.string.table_componenst_header_value_one),
                stringResource(id = R.string.table_componenst_header_value_two),
                stringResource(id = R.string.table_componenst_header_value_three),
                stringResource(id = R.string.table_componenst_header_value_four),
                stringResource(id = R.string.table_componenst_header_value_five)
            ),
            bodyList = bodyList
        )
    }
}

@Preview
@Composable
fun TextItemTablePreview() {
    StatisticshelperTheme {
        TextItemTable(text = "Classes")
    }
}