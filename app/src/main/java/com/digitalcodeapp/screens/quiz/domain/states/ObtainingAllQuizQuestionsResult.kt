package com.digitalcodeapp.screens.quiz.domain.states

import com.digitalcodeapp.screens.quiz.domain.models.DomainQuestion

sealed class ObtainingAllQuizQuestionsResult {

    data class Success(val domainQuestions: List<DomainQuestion>) : ObtainingAllQuizQuestionsResult()

    data object Error : ObtainingAllQuizQuestionsResult()
}
