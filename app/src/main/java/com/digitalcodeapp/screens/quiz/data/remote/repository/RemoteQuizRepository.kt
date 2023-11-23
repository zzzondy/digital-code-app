package com.digitalcodeapp.screens.quiz.data.remote.repository

import com.digitalcodeapp.screens.quiz.data.remote.states.RemoteObtainingAllQuizQuestionsResult

interface RemoteQuizRepository {

    suspend fun obtainAllQuizQuestions(): RemoteObtainingAllQuizQuestionsResult
}