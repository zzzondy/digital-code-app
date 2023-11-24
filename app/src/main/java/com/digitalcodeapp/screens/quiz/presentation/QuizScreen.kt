package com.digitalcodeapp.screens.quiz.presentation

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.digitalcodeapp.R
import com.digitalcodeapp.common.ui.components.LoadingDialog
import com.digitalcodeapp.common.ui.components.SomeErrorComponent
import com.digitalcodeapp.common.utils.collectAsEffect
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenAction
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenEffect
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenState
import com.digitalcodeapp.screens.quiz.presentation.states.ui.QuizScreenContentState
import com.digitalcodeapp.screens.quiz.presentation.states.ui.QuizScreenFinishState
import com.digitalcodeapp.screens.quiz.presentation.states.ui.QuizScreenLoadingState
import com.digitalcodeapp.screens.quiz.presentation.states.ui.QuizScreenStartingState

@Composable
fun QuizScreen(
    navController: NavController,
    viewModel: QuizScreenViewModel = hiltViewModel(),
) {

    var isWarningDialogEnabled by remember {
        mutableStateOf(false)
    }

    var finishingDialogState by remember {
        mutableIntStateOf(-1)
    }

    var isLoadingDialogVisible by remember {
        mutableStateOf(false)
    }

    BackHandler {
        if (viewModel.state.value is QuizScreenState.Content) {
            isWarningDialogEnabled = true
        } else {
            navController.popBackStack()
        }
    }

    viewModel.effect.collectAsEffect { effect ->
        when (effect) {
            is QuizScreenEffect.NavigateBack -> {
                navController.popBackStack()
            }

            is QuizScreenEffect.ShowWarningDialog -> {
                isWarningDialogEnabled = true
            }

            is QuizScreenEffect.ShowFinishingDialog -> {
                finishingDialogState = effect.numberOfUnansweredQuestions
            }

            is QuizScreenEffect.ShowLoadingDialog -> {
                isLoadingDialogVisible = true
            }

            is QuizScreenEffect.HideLoadingDialog -> {
                isLoadingDialogVisible = false
            }
        }
    }

    if (isLoadingDialogVisible) {
        LoadingDialog()
    }

    if (isWarningDialogEnabled) {
        AlertDialog(
            onDismissRequest = {
                isWarningDialogEnabled = false
            },
            confirmButton = {
                OutlinedButton(onClick = { navController.popBackStack() }) {
                    Text(text = stringResource(R.string.exit))
                }
            },
            dismissButton = {
                Button(onClick = { isWarningDialogEnabled = false }) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.are_you_sure_you_want_exit) + " " + stringResource(
                        R.string.all_progress_will_be_lost
                    )
                )
            },
        )
    }

    if (finishingDialogState != -1) {
        AlertDialog(
            onDismissRequest = {
                finishingDialogState = -1
            },
            confirmButton = {
                OutlinedButton(onClick = {
                    finishingDialogState = -1
                    viewModel.onAction(QuizScreenAction.OnFinishQuiz)
                }) {
                    Text(text = stringResource(R.string.finish))
                }
            },
            dismissButton = {
                Button(onClick = { finishingDialogState = -1 }) {
                    Text(text = stringResource(R.string.cancel))
                }
            },
            title = {
                Text(
                    text = stringResource(R.string.are_you_sure_you_want_exit) + if (finishingDialogState != 0) {
                        " " + pluralStringResource(
                            R.plurals.you_have_unanswered_questions,
                            finishingDialogState,
                            finishingDialogState
                        )
                    } else {
                        ""
                    }
                )
            },
        )
    }

    QuizScreenContent(
        state = viewModel.state.collectAsState().value,
        onAction = viewModel::onAction
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun QuizScreenContent(
    state: QuizScreenState,
    onAction: (QuizScreenAction) -> Unit = {},
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.quiz)) },
                navigationIcon = {
                    IconButton(
                        onClick = { onAction(QuizScreenAction.OnBackButtonClicked) }
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
        when (state) {
            is QuizScreenState.Starting -> {
                QuizScreenStartingState(
                    numberOfQuestions = state.numberOfQuestions,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onStartButtonClicked = {
                        onAction(QuizScreenAction.OnStartButtonClicked)
                    }
                )
            }

            is QuizScreenState.Content -> {
                QuizScreenContentState(
                    questions = state.questions,
                    onSelectAnswer = { numberOfQuestion, selectedAnswer ->
                        onAction(
                            QuizScreenAction.OnSelectAnswer(
                                numberOfQuestion,
                                selectedAnswer
                            )
                        )
                    },
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onFinishButtonClicked = {
                        onAction(QuizScreenAction.OnFinishButtonClicked)
                    }
                )
            }

            is QuizScreenState.Loading -> {
                QuizScreenLoadingState(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is QuizScreenState.Finish -> {
                QuizScreenFinishState(
                    numberOfRightQuestions = state.numberOfRightAnswers,
                    numberOfQuestions = state.numberOfQuestions,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    onFinishButtonClicked = {
                        onAction(QuizScreenAction.OnBackButtonClicked)
                    }
                )
            }

            is QuizScreenState.Error -> {
                SomeErrorComponent(
                    modifier = Modifier.fillMaxSize(),
                    onRefreshClicked = { onAction(QuizScreenAction.OnRefreshButtonClicked) }
                )
            }
        }
    }
}