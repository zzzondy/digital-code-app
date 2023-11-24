package com.digitalcodeapp.screens.quiz.presentation.states

sealed class QuizScreenEffect {

    data object NavigateBack : QuizScreenEffect()

    data object ShowWarningDialog : QuizScreenEffect()

    data class ShowFinishingDialog(val numberOfUnansweredQuestions: Int) : QuizScreenEffect()

    data object ShowLoadingDialog : QuizScreenEffect()

    data object HideLoadingDialog : QuizScreenEffect()
}