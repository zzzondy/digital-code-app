package com.digitalcodeapp.screens.pocket_money.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.pocket_money.presentation.states.PocketMoneyScreenAction
import com.digitalcodeapp.screens.pocket_money.presentation.states.PocketMoneyScreenEffect

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PocketMoneyScreen(
    navController: NavController,
    viewModel: PocketMoneyScreenViewModel = hiltViewModel()
) {
    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            PocketMoneyScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }
        }
    }

    val uriHandler = LocalUriHandler.current
    val link1 = stringResource(R.string.link1)
    val link2 = stringResource(R.string.link2)
    val link3 = stringResource(R.string.link3)

    val alphaLink = stringResource(R.string.alpha_link)
    val tinkoffLink = stringResource(R.string.tinkoff_link)
    val sberLink = stringResource(R.string.sber_link)
    val vtbLink = stringResource(R.string.vtb_link)

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.pocket_money)) },
                navigationIcon = {
                    IconButton(
                        onClick = { viewModel.onAction(PocketMoneyScreenAction.OnBackButtonClicked) }
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
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {

            Image(
                painter = painterResource(R.drawable.savings),
                contentDescription = stringResource(R.string.pocket_money_icon),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
                modifier = Modifier.size(100.dp)
            )

            Spacer(modifier = Modifier.height(32.dp))

            Text(
                text = stringResource(R.string.there_are_several_topics),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(onClick = { uriHandler.openUri(link1) }) {
                Text(text = stringResource(R.string.link, 1))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { uriHandler.openUri(link2) }) {
                Text(text = stringResource(R.string.link, 2))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(onClick = { uriHandler.openUri(link3) }) {
                Text(text = stringResource(R.string.link, 3))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = stringResource(R.string.for_parents),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.alfa),
                contentDescription = stringResource(R.string.alfa),
                modifier = Modifier.clickable {
                    uriHandler.openUri(alphaLink)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.tinkoff),
                contentDescription = stringResource(R.string.tinkoff),
                modifier = Modifier.clickable {
                    uriHandler.openUri(tinkoffLink)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.sber),
                contentDescription = stringResource(R.string.sber_link),
                modifier = Modifier.clickable {
                    uriHandler.openUri(sberLink)
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Image(
                painter = painterResource(R.drawable.vtb),
                contentDescription = stringResource(R.string.vtb),
                modifier = Modifier.clickable {
                    uriHandler.openUri(vtbLink)
                }
            )
        }
    }
}