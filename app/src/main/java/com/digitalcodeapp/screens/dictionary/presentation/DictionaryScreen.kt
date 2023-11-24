package com.digitalcodeapp.screens.dictionary.presentation

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.digitalcodeapp.common.ui.components.SomeErrorComponent
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.dictionary.domain.models.DictionaryTerm
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenAction
import com.digitalcodeapp.screens.dictionary.presentation.states.DictionaryScreenEffect
import com.digitalcodeapp.screens.dictionary.presentation.states.ui.DictionaryScreenContentState
import com.digitalcodeapp.screens.dictionary.presentation.states.ui.DictionaryScreenLoadingState

@Composable
fun DictionaryScreen(
    navController: NavController,
    viewModel: DictionaryScreenViewModel = hiltViewModel(),
) {
    val dictionaryTerms = viewModel.dictionaryTerms.collectAsLazyPagingItems()

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            DictionaryScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }

            DictionaryScreenEffect.RefreshData -> {
                dictionaryTerms.refresh()
            }
        }
    }
    DictionaryScreenContent(
        dictionaryTerms = dictionaryTerms,
        onAction = viewModel::onAction
    )
}

@Composable
private fun DictionaryScreenContent(
    dictionaryTerms: LazyPagingItems<DictionaryTerm>,
    onAction: (DictionaryScreenAction) -> Unit = {},
) {
    var searchingQuery by rememberSaveable { mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .pointerInput(Unit) {
                detectTapGestures { focusManager.clearFocus() }
            },
        topBar = {
            DictionaryScreenTopAppBar(
                searchQuery = searchingQuery,
                onBackButtonClicked = { onAction(DictionaryScreenAction.OnBackArrowClicked) },
                onSearchQueryUpdated = { query ->
                    searchingQuery = query
                    onAction(DictionaryScreenAction.OnEnteredNewQuery(query))
                },
                onClearButtonClicked = {
                    searchingQuery = ""
                    onAction(DictionaryScreenAction.OnEnteredNewQuery(""))
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
            )
        },
    ) { paddingValues ->
        dictionaryTerms.apply {
            AnimatedContent(
                targetState = loadState.refresh,
                label = "dictionary screen animated content",
                modifier = Modifier.padding(top = 16.dp)
            ) {
                when (it) {
                    is LoadState.Loading -> {
                        DictionaryScreenLoadingState(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                    }

                    is LoadState.Error -> {
                        SomeErrorComponent(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            onRefreshClicked = {
                                onAction(DictionaryScreenAction.OnRefreshButtonClicked)
                            }
                        )
                    }

                    else -> {
                        DictionaryScreenContentState(
                            dictionaryTerms = dictionaryTerms,
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues)
                        )
                    }
                }
            }
        }
    }
}