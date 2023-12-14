package com.digitalcodeapp.screens.feedback.domain.repository

import com.digitalcodeapp.screens.feedback.domain.models.FeedbackForm
import com.digitalcodeapp.screens.feedback.domain.states.SendingFeedbackResult

interface FeedbackRepository {

    suspend fun sendFeedback(feedbackForm: FeedbackForm): SendingFeedbackResult
}