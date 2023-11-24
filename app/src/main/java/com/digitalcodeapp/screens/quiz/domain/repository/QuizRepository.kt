package com.digitalcodeapp.screens.quiz.domain.repository

import com.digitalcodeapp.screens.quiz.domain.states.ObtainingAllQuizQuestionsResult

interface QuizRepository {

    suspend fun obtainAllQuizQuestions(): ObtainingAllQuizQuestionsResult
}