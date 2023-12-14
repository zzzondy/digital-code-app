package com.digitalcodeapp.screens.quiz.domain.use_cases

import com.digitalcodeapp.screens.quiz.domain.models.Question

class CheckAnswersOnQuestionsUseCase {

    operator fun invoke(questions: List<Question>): Int {
        var numberOfRightAnswers = 0
        questions.forEach { question ->
            when (question) {
                is Question.QuestionWithSingleAnswer -> {
                    if (question.selectedAnswer == question.domainQuestion.rightAnswers[0]) {
                        numberOfRightAnswers++
                    }
                }

                is Question.QuestionWithMultipleAnswers -> {
                    if (question.selectedAnswers == question.domainQuestion.rightAnswers) {
                        numberOfRightAnswers++
                    }
                }
            }
        }
        return numberOfRightAnswers
    }
}