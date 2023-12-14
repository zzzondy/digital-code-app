package com.digitalcodeapp.screens.quiz.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.common.dispatchers.DispatchersProvider
import com.digitalcodeapp.screens.quiz.domain.models.Question
import com.digitalcodeapp.screens.quiz.domain.states.ObtainingAllQuizQuestionsResult
import com.digitalcodeapp.screens.quiz.domain.use_cases.CheckAnswersOnQuestionsUseCase
import com.digitalcodeapp.screens.quiz.domain.use_cases.ObtainAllQuizQuestionsUseCase
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenAction
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenEffect
import com.digitalcodeapp.screens.quiz.presentation.states.QuizScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuizScreenViewModel @Inject constructor(
    private val obtainAllQuizQuestionsUseCase: ObtainAllQuizQuestionsUseCase,
    private val checkAnswersOnQuestionsUseCase: CheckAnswersOnQuestionsUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel() {

    private var questions = listOf<Question>()

    private var _state = MutableStateFlow<QuizScreenState>(QuizScreenState.Loading)

    val state = _state.asStateFlow()

    private var _effect = MutableSharedFlow<QuizScreenEffect>()
    val effect = _effect.asSharedFlow()

    init {
        prepareQuiz()
    }

    private fun prepareQuiz() {
        viewModelScope.launch(dispatchersProvider.io) {
            when (val result = obtainAllQuizQuestionsUseCase()) {
                is ObtainingAllQuizQuestionsResult.Success -> {
                    val newQuestions = questions.toMutableList()

                    result.domainQuestions.forEach { question ->
                        val presentationQuestion = if (question.rightAnswers.size == 1) {
                            Question.QuestionWithSingleAnswer(
                                domainQuestion = question,
                                shuffledAnswers = getShuffledAnswers(question.rightAnswers, question.irregularAnswers),
                                selectedAnswer = ""
                            )
                        } else {
                            Question.QuestionWithMultipleAnswers(
                                domainQuestion = question,
                                shuffledAnswers = getShuffledAnswers(question.rightAnswers, question.irregularAnswers),
                                selectedAnswers = listOf()
                            )
                        }
                        newQuestions.add(presentationQuestion)
                    }

                    questions = newQuestions
                    _state.update {
                        QuizScreenState.Starting(questions.size)
                    }
                }

                is ObtainingAllQuizQuestionsResult.Error -> {
                    _state.update {
                        QuizScreenState.Error
                    }
                }
            }
        }
    }

    private fun getShuffledAnswers(
        rightAnswers: List<String>,
        irregularAnswers: List<String>
    ): List<String> =
        rightAnswers.plus(irregularAnswers).shuffled()


    fun onAction(action: QuizScreenAction) {
        when (action) {
            is QuizScreenAction.OnBackButtonClicked -> onBackButtonClicked()

            is QuizScreenAction.OnRefreshButtonClicked -> onRefreshButtonClicked()

            is QuizScreenAction.OnSelectAnswer -> onSelectAnswer(
                numberOfQuestion = action.numberOfQuestion,
                selectedAnswer = action.selectedAnswer
            )

            is QuizScreenAction.OnStartButtonClicked -> onStartButtonClicked()

            is QuizScreenAction.OnFinishButtonClicked -> onFinishButtonClicked()

            is QuizScreenAction.OnFinishQuiz -> onFinishQuiz()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            if (state.value !is QuizScreenState.Content) {
                _effect.emit(QuizScreenEffect.NavigateBack)
            } else {
                _effect.emit(QuizScreenEffect.ShowWarningDialog)
            }
        }
    }

    private fun onRefreshButtonClicked() {
        viewModelScope.launch {
            _state.update {
                QuizScreenState.Loading
            }

            prepareQuiz()
        }
    }

    private fun onSelectAnswer(
        numberOfQuestion: Int,
        selectedAnswer: String,
    ) {
        viewModelScope.launch {
            var requestedQuestion = questions[numberOfQuestion]

            when (requestedQuestion) {
                is Question.QuestionWithSingleAnswer -> {
                    requestedQuestion = requestedQuestion.copy(selectedAnswer = selectedAnswer)
                }

                is Question.QuestionWithMultipleAnswers -> {
                    val selectedAnswers = requestedQuestion.selectedAnswers.toMutableList()

                    if (selectedAnswers.contains(selectedAnswer)) {
                        selectedAnswers.remove(selectedAnswer)
                    } else {
                        selectedAnswers.add(selectedAnswer)
                    }

                    requestedQuestion =
                        requestedQuestion.copy(selectedAnswers = selectedAnswers.toList())
                }
            }

            val newQuestions = questions.toMutableList()
            newQuestions[numberOfQuestion] = requestedQuestion
            questions = newQuestions.toList()

            _state.update {
                QuizScreenState.Content(questions = questions)
            }
        }
    }

    private fun onStartButtonClicked() {
        viewModelScope.launch {
            _state.update {
                QuizScreenState.Content(questions = questions)
            }
        }
    }

    private fun onFinishButtonClicked() {
        viewModelScope.launch {
            var numberOfUnansweredQuestions = 0
            questions.forEach { question ->
                when (question) {
                    is Question.QuestionWithSingleAnswer -> {
                        if (question.selectedAnswer.isBlank()) {
                            numberOfUnansweredQuestions++
                        }
                    }

                    is Question.QuestionWithMultipleAnswers -> {
                        if (question.selectedAnswers.isEmpty()) {
                            numberOfUnansweredQuestions++
                        }
                    }
                }
            }

            _effect.emit(QuizScreenEffect.ShowFinishingDialog(numberOfUnansweredQuestions = numberOfUnansweredQuestions))
        }
    }

    private fun onFinishQuiz() {
        viewModelScope.launch {
            _effect.emit(QuizScreenEffect.ShowLoadingDialog)

            val result = checkAnswersOnQuestionsUseCase(questions)

            _effect.emit(QuizScreenEffect.HideLoadingDialog)
            _state.update {
                QuizScreenState.Finish(
                    numberOfRightAnswers = result,
                    numberOfQuestions = questions.size
                )
            }
        }
    }

}