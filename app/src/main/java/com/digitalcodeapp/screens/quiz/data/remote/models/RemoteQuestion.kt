package com.digitalcodeapp.screens.quiz.data.remote.models

import androidx.annotation.Keep


@Keep
data class RemoteQuestion(
    val id: Long = 0,
    val question: String= "",
    val rightAnswers: List<String> = emptyList(),
    val irregularAnswers: List<String> = emptyList(),
)
