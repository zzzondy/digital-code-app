package com.digitalcodeapp.screens.quiz.presentation.states

sealed class QuizScreenAction {

    data object OnBackButtonClicked : QuizScreenAction()

    data object OnRefreshButtonClicked : QuizScreenAction()

    data class OnSelectAnswer(val numberOfQuestion: Int, val selectedAnswer: String) : QuizScreenAction()

    data object OnStartButtonClicked : QuizScreenAction()

    data object OnFinishButtonClicked : QuizScreenAction()

    data object OnFinishQuiz : QuizScreenAction()
}