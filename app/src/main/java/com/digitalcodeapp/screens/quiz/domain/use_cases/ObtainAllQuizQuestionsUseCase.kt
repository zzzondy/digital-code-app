package com.digitalcodeapp.screens.quiz.domain.use_cases

import com.digitalcodeapp.screens.quiz.domain.repository.QuizRepository

class ObtainAllQuizQuestionsUseCase(private val quizRepository: QuizRepository) {

    suspend operator fun invoke() = quizRepository.obtainAllQuizQuestions()
}