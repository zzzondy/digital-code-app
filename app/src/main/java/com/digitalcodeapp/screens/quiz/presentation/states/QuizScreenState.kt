package com.digitalcodeapp.screens.quiz.presentation.states

import com.digitalcodeapp.screens.quiz.domain.models.Question

sealed class QuizScreenState {

    data object Loading : QuizScreenState()

    data class Starting(val numberOfQuestions: Int) : QuizScreenState()

    data class Content(val questions: List<Question>) : QuizScreenState()

    data class Finish(val numberOfRightAnswers: Int, val numberOfQuestions: Int) : QuizScreenState()

    data object Error : QuizScreenState()
}
