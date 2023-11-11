package com.digitalcodeapp.screens.main

import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.navigation.graphs.main_graph.Screens
import com.digitalcodeapp.screens.main.states.MainScreenAction
import com.digitalcodeapp.screens.main.states.MainScreenEffect
import com.digitalcodeapp.ui.theme.DigitalCodeAppTheme

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
) {
    Scaffold(
        topBar = {
            MainScreenTopAppBar()
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            item(key = "dictionary") {
                ElevatedCard(
                    onClick = onDictionarySectionClicked,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .padding(16.dp),
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
                                text = stringResource(R.string.dictionary),
                                style = MaterialTheme.typography.titleLarge
                            )

                            Spacer(modifier = Modifier.height(8.dp))

                            Text(
                                text = stringResource(R.string.dictionary_details),
                                style = MaterialTheme.typography.labelLarge,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }


                        Icon(
                            painter = painterResource(R.drawable.school),
                            contentDescription = stringResource(R.string.dictionary_icon),
                            tint = MaterialTheme.colorScheme.primary,
                            modifier = Modifier
                                .padding(end = 16.dp)
                                .size(64.dp)
                                .align(Alignment.CenterVertically)
                        )
                    }
                }
            }
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