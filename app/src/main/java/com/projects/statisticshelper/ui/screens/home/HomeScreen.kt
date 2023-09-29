package com.projects.statisticshelper.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.projects.statisticshelper.MainActivity
import com.projects.statisticshelper.R
import com.projects.statisticshelper.model.CalculatorType
import com.projects.statisticshelper.model.HomeItemsModel
import com.projects.statisticshelper.ui.theme.StatisticshelperTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onItemClick: (CalculatorType) -> Unit
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                onNavigationIconClick = null,
                onActionClick = null
            )
        }
    ) {
        HomeContent(Modifier.padding(it)) { calculatorType ->
            onItemClick.invoke(calculatorType)
        }
    }
}

@Composable
private fun HomeContent(modifier: Modifier = Modifier, onItemClick: (CalculatorType) -> Unit) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_margin_xxl)))
        Text(
            text = stringResource(id = R.string.home_information_text),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_margin_m)))
        ButtonList {
            onItemClick.invoke(it)
        }
    }
}

@Composable
private fun ButtonList(
    modifier: Modifier = Modifier,
    homeItems: List<HomeItemsModel> = MainActivity.HOME_ITEMS_MODEL,
    onItemClick: (CalculatorType) -> Unit
) {
    LazyColumn(modifier = modifier) {
        items(homeItems) {
            ButtonItem(it) { calculatorType ->
                onItemClick.invoke(calculatorType)
            }
        }
    }
}


@Composable
private fun ButtonItem(homeItem: HomeItemsModel, onItemClick: (CalculatorType) -> Unit) {
    val minHeight = 65.dp

    Button(
        modifier = Modifier
            .padding(dimensionResource(id = R.dimen.dimen_margin_xs))
            .heightIn(minHeight)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary
        ),
        shape = RoundedCornerShape(dimensionResource(id = R.dimen.dimen_shape_xs)),
        onClick = { onItemClick.invoke(homeItem.calculatorType) }) {
        Text(
            text = homeItem.text,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeTopAppBar(
    onNavigationIconClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.home_top_bar_title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(id = R.string.accessibility_icon_menu)
                )
            }
        },
        actions = {
            IconButton(onClick = { onActionClick?.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.History,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(id = R.string.accessibility_icon_history)
                )
            }
        }
    )
}


@Preview(
    showBackground = true,
)
@Preview(
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Composable
fun TopAppBarPreview() {
    StatisticshelperTheme {
        HomeTopAppBar()
    }
}

@Preview()
@Composable
fun ButtonItemPreview() {
    StatisticshelperTheme(dynamicColor = false) {
        ButtonItem(MainActivity.HOME_ITEMS_MODEL[0]) { }
    }
}


@Preview()
@Composable
fun DefaultPreview() {
    StatisticshelperTheme(dynamicColor = false) {
        HomeScreen(modifier = Modifier.fillMaxSize()) { }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    name = "Dark"
)
@Preview(
    showBackground = true,
    widthDp = 320
)
@Composable
fun GreetingsPreview() {
    StatisticshelperTheme(dynamicColor = false) {
        ButtonList(homeItems = MainActivity.HOME_ITEMS_MODEL) { }
    }
}