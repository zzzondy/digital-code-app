package com.digitalcodeapp.screens.quiz.domain.models

sealed class Question {
    data class QuestionWithSingleAnswer(
        val domainQuestion: DomainQuestion,
        val shuffledAnswers: List<String>,
        val selectedAnswer: String,
    ) : Question()

    data class QuestionWithMultipleAnswers(
        val domainQuestion: DomainQuestion,
        val shuffledAnswers: List<String>,
        val selectedAnswers: List<String>,
    ) : Question()
}
