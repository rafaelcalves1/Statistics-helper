package com.projects.statisticshelper.ui.screens.inputvalors

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.projects.statisticshelper.R
import com.projects.statisticshelper.model.CalculatorType
import com.projects.statisticshelper.ui.theme.StatisticshelperTheme
import com.projects.statisticshelper.util.commonBorderForInput
import com.projects.statisticshelper.util.fontDimensionResource

@Composable
fun InputValorsScreen(
    calculatorType: CalculatorType,
    onNavigationIconClick: () -> Unit = {},
    viewModel: InputValorsViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.updateCalculatorType(calculatorType)
    }

    Scaffold(
        modifier = Modifier.scrollable(rememberScrollState(), Orientation.Vertical),
        topBar = {
            InputValorsTopBar(onNavigationIconClick)
        }
    ) {
        Column(
            Modifier.padding(it),
            verticalArrangement = Arrangement.Center
        ) {
            InputValorsContent(
                viewState = viewState,
                onTextChanged = {
                    viewModel.updateActualText(it)
                }
            )
            Spacer(modifier = Modifier.padding(top = dimensionResource(id = R.dimen.dimen_margin_m)))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.dimen_margin_xs)),
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    modifier = Modifier
                        .heightIn(dimensionResource(id = R.dimen.dimen_button_common_height_size))
                        .widthIn(dimensionResource(id = R.dimen.dimen_button_common_width_size)),
                    onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.input_valor_calculate))
                }
            }
        }
    }
}

@Composable
fun InputValorsContent(
    viewState: InputValorsViewState,
    onTextChanged: (String?) -> Unit
) {
    var shouldClearText by remember { mutableStateOf(false) }
    val paddingValue = dimensionResource(id = R.dimen.dimen_margin_xs)

    Spacer(Modifier.padding(top = dimensionResource(id = R.dimen.dimen_margin_xxl)))

    Text(
        text = stringResource(id = R.string.input_valor_information_text),
        style = MaterialTheme.typography.titleMedium,
        fontSize = fontDimensionResource(id = R.dimen.dimen_font_size_m),
        modifier = Modifier.padding(dimensionResource(id = R.dimen.dimen_margin_xs))
    )

    LabelValorsText(
        modifier = Modifier
            .padding(
                top = paddingValue,
                start = paddingValue,
                end = paddingValue
            ),
        shouldClearText = shouldClearText,
        viewState = viewState,
        onTextChanged = { onTextChanged.invoke(it) },
        onClearText = { shouldClearText = it }
    )

    AnimatedVisibility(
        visible = viewState.isClearTextVisible,
        enter = slideInVertically() + expandVertically(
            expandFrom = Alignment.Top
        ) + fadeIn(
            initialAlpha = 0.3f
        ),
        exit = shrinkVertically()
    ) {
        ClearTextButton(
            modifier = Modifier
                .padding(
                    end = paddingValue,
                    start = paddingValue
                )
        ) {
            shouldClearText = shouldClearText.not()
        }

    }

}

@Composable
fun LabelValorsText(
    modifier: Modifier = Modifier,
    shouldClearText: Boolean = false,
    viewState: InputValorsViewState,
    onTextChanged: (String?) -> Unit,
    onClearText: (Boolean) -> Unit
) {
    var actualTextRemeber by rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(
            TextFieldValue()
        )
    }

    val minHeight = 150.dp

    Column(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .commonBorderForInput()
    ) {

        if (shouldClearText) {
            actualTextRemeber = TextFieldValue()
            onClearText.invoke(false)
            onTextChanged.invoke(null)
        }

        TextField(
            value = actualTextRemeber,
            onValueChange = { newText ->
                actualTextRemeber = newText
                onTextChanged.invoke(actualTextRemeber.text)
            },
            keyboardOptions = viewState.keyboardOptions,
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(minHeight)
                .background(MaterialTheme.colorScheme.background)
        )
    }
}

@Composable
fun ClearTextButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Row(modifier = modifier) {
        Button(
            modifier = Modifier
                .commonBorderForInput(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),
            onClick = { onClick.invoke() }
        ) {
            Icon(
                Icons.Filled.Cancel,
                contentDescription = null,
                tint = Color.Black
            )
            Text(
                text = "Limpar",
                style = MaterialTheme.typography.titleSmall,
                color = Color.Black
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputValorsTopBar(
    onNavigationIconClick: () -> Unit = {}
) {
    TopAppBar(
        title = { Text(text = stringResource(id = R.string.input_valor_top_bar_title)) },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        navigationIcon = {
            IconButton(onClick = { onNavigationIconClick.invoke() }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onPrimary,
                    contentDescription = stringResource(id = R.string.accessibility_icon_back)
                )
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun ClearTextButtonPreview() {
    StatisticshelperTheme {
        ClearTextButton { }
    }
}

@Preview(showBackground = true)
@Composable
fun InputValorsScreenPreview() {
    StatisticshelperTheme {
        InputValorsScreen(
            CalculatorType.CONTINUOUS_DATA
        )
    }
}

@Preview
@Composable
fun InputValorsTopBarPreview() {
    StatisticshelperTheme {
        InputValorsTopBar()
    }
}