package com.digitalcodeapp.screens.quiz.domain.models

data class DomainQuestion(
    val id: Long,
    val question: String,
    val rightAnswers: List<String>,
    val irregularAnswers: List<String>,
)
