package com.digitalcodeapp.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.ui.theme.DigitalCodeAppTheme
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.navigation.graphs.main_graph.Screens
import com.digitalcodeapp.screens.main.states.MainScreenAction
import com.digitalcodeapp.screens.main.states.MainScreenEffect

@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            MainScreenEffect.NavigateToDictionaryScreen -> {
                navController.navigate(Screens.DictionaryScreen.route)
            }
        }
    }

    MainScreenContent(
        onDictionarySectionClicked = {
            viewModel.onAction(MainScreenAction.OnDictionarySectionClicked)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    onDictionarySectionClicked: () -> Unit = {},
    onPocketMoneySectionClicked: () -> Unit = {},
    onHistoryOfRubleSectionClicked: () -> Unit = {},
    onFinancialScamSectionClicked: () -> Unit = {},
    onQuizSectionClicked: () -> Unit = {},
) {
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()

    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = { Text(text = stringResource(R.string.main_screen)) },
                scrollBehavior = scrollBehavior
            )
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .nestedScroll(scrollBehavior.nestedScrollConnection),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item(key = "dictionary") {
                Section(
                    label = stringResource(R.string.dictionary),
                    description = stringResource(R.string.dictionary_details),
                    icon = painterResource(R.drawable.school),
                    iconContentDescription = stringResource(R.string.dictionary_icon),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    onSectionClicked = onDictionarySectionClicked,
                )
            }

            item(key = "pocket_money") {
                Section(
                    label = stringResource(R.string.pocket_money),
                    description = stringResource(
                        R.string.pocket_money_description
                    ),
                    icon = painterResource(R.drawable.savings),
                    iconContentDescription = stringResource(R.string.pocket_money_icon),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    onSectionClicked = onPocketMoneySectionClicked,
                )
            }

            item(key = "financial_scam") {
                Section(
                    label = stringResource(R.string.financial_scam),
                    description = stringResource(
                        R.string.financial_scam_description
                    ),
                    icon = painterResource(R.drawable.phishing),
                    iconContentDescription = stringResource(
                        R.string.financial_scam_icon
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    onSectionClicked = onFinancialScamSectionClicked,
                )
            }

            item(key = "history_of_ruble") {
                Section(
                    label = stringResource(R.string.history_of_ruble),
                    description = stringResource(
                        R.string.history_of_ruble_description
                    ),
                    icon = painterResource(R.drawable.currency_ruble),
                    iconContentDescription = stringResource(
                        R.string.history_of_ruble_icon
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    onSectionClicked = onHistoryOfRubleSectionClicked,
                )
            }

            item(key = "quiz") {
                Section(
                    label = stringResource(R.string.quiz),
                    description = stringResource(R.string.quiz_description),
                    icon = painterResource(R.drawable.quiz),
                    iconContentDescription = stringResource(R.string.quiz_icon),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(125.dp),
                    onSectionClicked = onQuizSectionClicked,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Section(
    label: String,
    description: String,
    icon: Painter,
    iconContentDescription: String,
    modifier: Modifier = Modifier,
    onSectionClicked: () -> Unit = {}
) {
    ElevatedCard(
        onClick = onSectionClicked,
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 16.dp, top = 16.dp, end = 8.dp, bottom = 16.dp)
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleLarge
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = description,
                    style = MaterialTheme.typography.labelLarge,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            Icon(
                painter = icon,
                contentDescription = iconContentDescription,
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(64.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}

@Preview(locale = "ru")
@Composable
fun MainScreenPreview() {
    DigitalCodeAppTheme {
        MainScreenContent()
    }
}