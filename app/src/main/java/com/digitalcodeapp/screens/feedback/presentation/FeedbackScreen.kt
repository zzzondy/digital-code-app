package com.digitalcodeapp.screens.feedback.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.ui.components.LoadingDialog
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenAction
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenEffect
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenState
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenAction

@Composable
fun FeedbackScreen(
    navController: NavController,
    viewModel: FeedbackScreenViewModel = hiltViewModel(),
) {
    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    var isSuccessDialogVisible by remember {
        mutableStateOf(false)
    }

    var isErrorDialogVisible by remember {
        mutableStateOf(false)
    }

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            FeedbackScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }

            FeedbackScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            FeedbackScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }

            FeedbackScreenEffect.ShowErrorDialog -> {
                isErrorDialogVisible = true
            }

            FeedbackScreenEffect.ShowSuccessDialog -> {
                isSuccessDialogVisible = true
            }
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    if (isSuccessDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isSuccessDialogVisible = false
                navController.popBackStack()
            },
            confirmButton = {
                OutlinedButton(onClick = { navController.popBackStack() }) {
                    Text(text = stringResource(R.string.exit))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.success_feedback)
                )
            },
        )
    }

    if (isErrorDialogVisible) {
        AlertDialog(
            onDismissRequest = {
                isErrorDialogVisible = false
            },
            confirmButton = {
                OutlinedButton(onClick = { isErrorDialogVisible = false }) {
                    Text(text = stringResource(R.string.exit))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.error_feedback)
                )
            },
        )
    }

    FeedbackScreenContent(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FeedbackScreenContent(
    state: FeedbackScreenState,
    onAction: (FeedbackScreenAction) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.feedback)) },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(FeedbackScreenAction.OnBackButtonClicked) }
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
        when (state) {
            is FeedbackScreenState.Content -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    item {
                        OutlinedTextField(
                            value = state.message,
                            onValueChange = { onAction(FeedbackScreenAction.OnMessageTyped(it)) },
                            modifier = Modifier.fillMaxWidth(),
                            isError = state.isMessageError,
                            supportingText = {
                                if (state.isMessageError) {
                                    Text(text = stringResource(R.string.form_can_not_be_blank))
                                }
                            },
                            label = {
                                Text(text = stringResource(R.string.message))
                            },
                            maxLines = 10
                        )
                    }

                    item {
                        OutlinedTextField(
                            value = state.name,
                            onValueChange = { onAction(FeedbackScreenAction.OnNameTyped(it)) },
                            modifier = Modifier.fillMaxWidth(),
                            isError = state.isNameError,
                            supportingText = {
                                if (state.isNameError) {
                                    Text(text = stringResource(R.string.form_can_not_be_blank))
                                }
                            },
                            label = {
                                Text(text = stringResource(R.string.name))
                            },
                            maxLines = 1
                        )
                    }

                    item {
                        Button(
                            onClick = { onAction(FeedbackScreenAction.OnSendButtonClicked) },
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(text = stringResource(R.string.send))
                        }
                    }
                }
            }
        }
    }
}