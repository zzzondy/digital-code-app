package com.digitalcodeapp.screens.quiz.data.repository

import com.digitalcodeapp.screens.quiz.data.remote.repository.RemoteQuizRepository
import com.digitalcodeapp.screens.quiz.data.utils.toDomain
import com.digitalcodeapp.screens.quiz.domain.repository.QuizRepository
import com.digitalcodeapp.screens.quiz.domain.states.ObtainingAllQuizQuestionsResult

class QuizRepositoryImpl(
    private val remoteQuizRepository: RemoteQuizRepository,
) : QuizRepository {

    override suspend fun obtainAllQuizQuestions(): ObtainingAllQuizQuestionsResult =
        remoteQuizRepository.obtainAllQuizQuestions().toDomain()
}