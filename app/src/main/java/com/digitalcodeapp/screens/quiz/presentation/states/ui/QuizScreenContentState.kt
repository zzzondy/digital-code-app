package com.digitalcodeapp.screens.quiz.presentation.states.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.digitalcodeapp.R
import com.digitalcodeapp.common.ui.theme.DarkYellow
import com.digitalcodeapp.common.ui.theme.DigitalCodeAppTheme
import com.digitalcodeapp.screens.quiz.domain.models.Question
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun QuizScreenContentState(
    questions: List<Question>,
    onSelectAnswer: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
    onFinishButtonClicked: () -> Unit = {},
) {
    val pagerState = rememberPagerState(
        pageCount = { questions.size }
    )

    val coroutineScope = rememberCoroutineScope()

    val indicatorsState = rememberLazyListState()

    LaunchedEffect(pagerState.currentPage) {
        indicatorsState.animateScrollToItem(pagerState.currentPage)
    }

    Column(
        modifier = modifier,
    ) {
        LazyRow(
            state = indicatorsState,
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            items(pagerState.pageCount) { iteration ->
                val color = when (val question = questions[iteration]) {
                    is Question.QuestionWithMultipleAnswers -> {
                        if (question.selectedAnswers.isEmpty()) {
                            Color.LightGray
                        } else {
                            DarkYellow
                        }
                    }

                    is Question.QuestionWithSingleAnswer -> {
                        if (question.selectedAnswer.isBlank()) {
                            Color.LightGray
                        } else {
                            DarkYellow
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(48.dp)
                        .clickable {
                            coroutineScope.launch {
                                pagerState.animateScrollToPage(iteration)
                            }
                        }
                        .then(
                            if (pagerState.currentPage == iteration) {
                                Modifier.border(
                                    width = 2.dp,
                                    color = Color.DarkGray,
                                    shape = CircleShape
                                )
                            } else {
                                Modifier
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = (iteration + 1).toString(),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.background
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))


        Text(
            text = stringResource(
                R.string.question,
                pagerState.currentPage + 1,
                pagerState.pageCount
            ),
            color = MaterialTheme.colorScheme.primary,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 32.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Divider(
            modifier = Modifier.padding(horizontal = 32.dp)
        )

        HorizontalPager(
            state = pagerState,
            key = { it },
            modifier = Modifier
                .padding(vertical = 16.dp)
                .weight(1f)
        ) { page ->
            QuestionSection(
                numberOfQuestion = page + 1,
                presentationQuestion = questions[page],
                onSelectAnswer = onSelectAnswer,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, start = 32.dp, end = 32.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                },
                enabled = pagerState.canScrollBackward
            ) {
                Text(text = stringResource(R.string.back))
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    if (pagerState.canScrollForward) {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    } else {
                        onFinishButtonClicked()
                    }
                },
            ) {
                Text(
                    text = if (pagerState.canScrollForward) stringResource(R.string.next) else stringResource(
                        R.string.finish
                    )
                )
            }
        }
    }
}

@Composable
private fun QuestionSection(
    numberOfQuestion: Int,
    presentationQuestion: Question,
    onSelectAnswer: (Int, String) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            )
    ) {
        item {
            Text(
                text = if (presentationQuestion is Question.QuestionWithSingleAnswer) presentationQuestion.domainQuestion.question else
                    (presentationQuestion as Question.QuestionWithMultipleAnswers).domainQuestion.question,
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )
        }

        when (presentationQuestion) {
            is Question.QuestionWithSingleAnswer -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectableGroup(),
                    ) {
                        repeat(presentationQuestion.shuffledAnswers.size) { iteration ->
                            val answer = presentationQuestion.shuffledAnswers[iteration]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MaterialTheme.shapes.extraLarge)
                                    .selectable(
                                        selected = presentationQuestion.selectedAnswer == answer,
                                        onClick = {
                                            onSelectAnswer(numberOfQuestion - 1, answer)
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                RadioButton(
                                    selected = presentationQuestion.selectedAnswer == answer,
                                    onClick = {
                                        onSelectAnswer(numberOfQuestion - 1, answer)
                                    }
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = answer,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }

            is Question.QuestionWithMultipleAnswers -> {
                item {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .selectableGroup(),
                    ) {
                        repeat(presentationQuestion.shuffledAnswers.size) { iteration ->
                            val answer = presentationQuestion.shuffledAnswers[iteration]
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(MaterialTheme.shapes.extraLarge)
                                    .selectable(
                                        selected = presentationQuestion.selectedAnswers.contains(
                                            answer
                                        ),
                                        onClick = {
                                            onSelectAnswer(numberOfQuestion - 1, answer)
                                        }
                                    ),
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Checkbox(
                                    checked = presentationQuestion.selectedAnswers.contains(answer),
                                    onCheckedChange = {
                                        onSelectAnswer(numberOfQuestion - 1, answer)
                                    }
                                )

                                Spacer(modifier = Modifier.width(8.dp))

                                Text(
                                    text = answer,
                                    color = MaterialTheme.colorScheme.onBackground,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun QuizScreenContentStatePreview() {
    DigitalCodeAppTheme {
    }
}