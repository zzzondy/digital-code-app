package com.digitalcodeapp.screens.feedback.data.remote.repository

import com.digitalcodeapp.screens.feedback.data.remote.models.RemoteFeedbackForm
import com.digitalcodeapp.screens.feedback.data.remote.states.RemoteSendingFeedbackResult

interface RemoteFeedbackRepository {

    suspend fun sendFeedback(remoteFeedbackForm: RemoteFeedbackForm): RemoteSendingFeedbackResult
}