package com.digitalcodeapp.screens.quiz.presentation

import com.digitalcodeapp.screens.quiz.domain.models.Question

sealed class PresentationQuestion {

    data class QuestionWithSingleAnswer(
        val question: Question,
        val shuffledAnswers: List<String>,
        val selectedAnswer: String,
    ) : PresentationQuestion()

    data class QuestionWithMultipleAnswers(
        val question: Question,
        val shuffledAnswers: List<String>,
        val selectedAnswers: List<String>,
    ) : PresentationQuestion()
}
