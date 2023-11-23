package com.digitalcodeapp.screens.quiz.presentation.states

import com.digitalcodeapp.screens.quiz.presentation.PresentationQuestion

sealed class QuizScreenState {

    data object Loading : QuizScreenState()

    data class Starting(val numberOfQuestions: Int) : QuizScreenState()

    data class Content(val questions: List<PresentationQuestion>) : QuizScreenState()

    data class Finish(val numberOfRightAnswers: Int, val numberOfQuestions: Int) : QuizScreenState()

    data object Error : QuizScreenState()
}
