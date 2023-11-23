package com.digitalcodeapp.screens.quiz.data.remote.states

import com.digitalcodeapp.screens.quiz.data.remote.models.RemoteQuestion

sealed class RemoteObtainingAllQuizQuestionsResult {

    data class Success(val questions: List<RemoteQuestion>): RemoteObtainingAllQuizQuestionsResult()

    data object Error : RemoteObtainingAllQuizQuestionsResult()
}
