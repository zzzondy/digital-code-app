package com.digitalcodeapp.screens.quiz.domain.models

data class Question(
    val id: Long,
    val question: String,
    val rightAnswers: List<String>,
    val irregularAnswers: List<String>,
)
