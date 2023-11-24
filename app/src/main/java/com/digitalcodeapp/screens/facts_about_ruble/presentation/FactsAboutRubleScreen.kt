package com.digitalcodeapp.screens.facts_about_ruble.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.facts_about_ruble.presentation.states.FactsAboutRubleAction
import com.digitalcodeapp.screens.facts_about_ruble.presentation.states.FactsAboutRubleEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FactsAboutRubleScreen(
    navController: NavController,
    viewModel: FactsAboutRubleViewModel = hiltViewModel()
) {
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            FactsAboutRubleEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    val facts = listOf(
        stringResource(R.string.fact1),
        stringResource(R.string.fact2),
        stringResource(R.string.fact3),
        stringResource(R.string.fact4),
        stringResource(R.string.fact5),
        stringResource(R.string.fact6),
        stringResource(R.string.fact7),
        stringResource(R.string.fact8),
        stringResource(R.string.fact9),
        stringResource(R.string.fact10),
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.fact_about_ruble)) },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.onAction(FactsAboutRubleAction.OnBackButtonClicked) }
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
        },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        LazyColumn(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            contentPadding = PaddingValues(16.dp)
        ) {
            item {
                Image(
                    painter = painterResource(R.drawable.ruble_old),
                    contentDescription = stringResource(
                        R.string.fact_about_ruble
                    )
                )
            }

            itemsIndexed(facts) { index, fact ->
                Text(text = "${index + 1} $fact", style = MaterialTheme.typography.titleSmall)
            }
        }
    }
}