package com.digitalcodeapp.screens.quiz.data.remote.repository

import com.digitalcodeapp.screens.quiz.data.remote.models.RemoteQuestion
import com.digitalcodeapp.screens.quiz.data.remote.states.RemoteObtainingAllQuizQuestionsResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteQuizRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteQuizRepository {

    override suspend fun obtainAllQuizQuestions(): RemoteObtainingAllQuizQuestionsResult {
        return try {
            val result = RemoteObtainingAllQuizQuestionsResult.Success(
                fireStore.collection(QUIZ_COLLECTION)
                    .get()
                    .await()
                    .toObjects(RemoteQuestion::class.java)
            )

            if (result.questions.isEmpty()) {
                return RemoteObtainingAllQuizQuestionsResult.Error
            }

            result
        } catch (e: Exception) {
            RemoteObtainingAllQuizQuestionsResult.Error
        }
    }

    companion object {
        private const val QUIZ_COLLECTION = "quiz"
    }
}