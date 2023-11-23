package com.digitalcodeapp.screens.quiz.domain.states

import com.digitalcodeapp.screens.quiz.domain.models.Question

sealed class ObtainingAllQuizQuestionsResult {

    data class Success(val questions: List<Question>) : ObtainingAllQuizQuestionsResult()

    data object Error : ObtainingAllQuizQuestionsResult()
}
