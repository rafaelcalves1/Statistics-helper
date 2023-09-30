package com.projects.statisticshelper.ui.screens.components.table.model

import java.lang.IllegalStateException

data class TableItems(
    val tableItems: List<TableItem>
) {
    fun indexOfLastHeaderItem(): Int {
        if (tableItems.any { it is TableItem.Header }
                .not()) throw IllegalStateException("Need at least one TableItem.Header")

        val indexOf = tableItems.indexOfLast { it is TableItem.Header }

        return indexOf + 1
    }
}


sealed class TableItem {

    data class Header(
        val name: String
    ) : TableItem()

    data class Body(
        val name: String
    ) : TableItem()
}