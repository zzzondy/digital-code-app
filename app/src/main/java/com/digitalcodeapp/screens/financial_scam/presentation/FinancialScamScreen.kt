package com.digitalcodeapp.screens.financial_scam.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.financial_scam.presentation.states.FinancialScamScreenAction
import com.digitalcodeapp.screens.financial_scam.presentation.states.FinancialScamScreenEffect
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FinancialScamScreen(
    navController: NavController,
    viewModel: FinancialScamScreenViewModel = hiltViewModel()
) {
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            FinancialScamScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    val titles = listOf(stringResource(R.string.example), stringResource(R.string.tips))
    val pagerState = rememberPagerState(pageCount = { titles.size })
    var tabState by remember { mutableIntStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(pagerState.currentPage) {
        tabState = pagerState.currentPage
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.financial_scam)) },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.onAction(FinancialScamScreenAction.OnBackButtonClicked) }
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(
                                R.string.return_back
                            )
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            TabRow(selectedTabIndex = tabState) {
                titles.forEachIndexed { index, title ->
                    Tab(
                        selected = tabState == index,
                        onClick = {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(index)
                            }
                        },
                        text = {
                            Text(text = title)
                        }
                    )
                }
            }

            HorizontalPager(state = pagerState) { index ->
                if (index != 0) {
                    val tips = listOf(
                        stringResource(R.string.tip1),
                        stringResource(R.string.tip2),
                        stringResource(R.string.tip3),
                        stringResource(R.string.tip4),
                        stringResource(R.string.tip5),
                        stringResource(R.string.tip6)
                    )
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        contentPadding = PaddingValues(16.dp)
                    ) {
                        item {
                            Text(
                                text = stringResource(R.string.tips_title),
                                style = MaterialTheme.typography.titleMedium
                            )
                        }

                        items(tips) { tip ->
                            Text(
                                text = tip,
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                } else {
                    LazyColumn(
                        contentPadding = PaddingValues(16.dp),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        item {
                            Image(
                                painter = painterResource(R.drawable.fin_scam1),
                                contentDescription = stringResource(R.string.financial_scam)
                            )
                        }

                        item {
                            Text(
                                text = stringResource(R.string.communication_with_seller),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(R.drawable.fin_scam2),
                                contentDescription = stringResource(R.string.financial_scam)
                            )
                        }

                        item {
                            Text(
                                text = stringResource(R.string.link_to_phishing),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }

                        item {
                            Image(
                                painter = painterResource(R.drawable.fin_scam3),
                                contentDescription = stringResource(R.string.financial_scam)
                            )
                        }

                        item {
                            Text(
                                text = stringResource(R.string.after_payment),
                                style = MaterialTheme.typography.titleLarge
                            )
                        }
                    }
                }
            }
        }
    }
}