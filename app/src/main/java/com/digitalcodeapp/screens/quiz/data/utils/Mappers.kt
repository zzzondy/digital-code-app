package com.digitalcodeapp.screens.quiz.data.utils

import com.digitalcodeapp.screens.quiz.data.remote.models.RemoteQuestion
import com.digitalcodeapp.screens.quiz.data.remote.states.RemoteObtainingAllQuizQuestionsResult
import com.digitalcodeapp.screens.quiz.domain.models.DomainQuestion
import com.digitalcodeapp.screens.quiz.domain.states.ObtainingAllQuizQuestionsResult

fun RemoteObtainingAllQuizQuestionsResult.toDomain() =
    when (this) {
        is RemoteObtainingAllQuizQuestionsResult.Success -> ObtainingAllQuizQuestionsResult.Success(
            this.questions.map { it.toDomain() })

        is RemoteObtainingAllQuizQuestionsResult.Error -> ObtainingAllQuizQuestionsResult.Error
    }

private fun RemoteQuestion.toDomain() =
    DomainQuestion(
        id = id,
        question = question,
        rightAnswers = rightAnswers,
        irregularAnswers = irregularAnswers
    )