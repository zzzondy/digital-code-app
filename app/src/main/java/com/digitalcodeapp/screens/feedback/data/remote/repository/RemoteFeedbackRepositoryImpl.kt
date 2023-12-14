package com.digitalcodeapp.screens.feedback.data.remote.repository

import com.digitalcodeapp.screens.feedback.data.remote.models.RemoteFeedbackForm
import com.digitalcodeapp.screens.feedback.data.remote.states.RemoteSendingFeedbackResult
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class RemoteFeedbackRepositoryImpl(
    private val fireStore: FirebaseFirestore
) : RemoteFeedbackRepository {

    override suspend fun sendFeedback(remoteFeedbackForm: RemoteFeedbackForm): RemoteSendingFeedbackResult {
        val data = hashMapOf(
            MESSAGE to remoteFeedbackForm.message,
            NAME to remoteFeedbackForm.name
        )

        return try {
            fireStore.collection(FEEDBACK_COLLECTION)
                .add(data)
                .await()
            RemoteSendingFeedbackResult.Success
        } catch (e: Exception) {
            RemoteSendingFeedbackResult.Error
        }
    }

    companion object {
        private const val FEEDBACK_COLLECTION = "feedback"

        private const val MESSAGE = "message"
        private const val NAME = "name"
    }
}