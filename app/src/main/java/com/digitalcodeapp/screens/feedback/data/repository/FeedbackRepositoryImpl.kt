package com.digitalcodeapp.screens.feedback.data.repository

import com.digitalcodeapp.screens.feedback.data.remote.repository.RemoteFeedbackRepository
import com.digitalcodeapp.screens.feedback.data.utils.toDomain
import com.digitalcodeapp.screens.feedback.data.utils.toRemote
import com.digitalcodeapp.screens.feedback.domain.models.FeedbackForm
import com.digitalcodeapp.screens.feedback.domain.repository.FeedbackRepository
import com.digitalcodeapp.screens.feedback.domain.states.SendingFeedbackResult

class FeedbackRepositoryImpl(
    private val remoteFeedbackRepository: RemoteFeedbackRepository
) : FeedbackRepository {

    override suspend fun sendFeedback(feedbackForm: FeedbackForm): SendingFeedbackResult =
        remoteFeedbackRepository.sendFeedback(feedbackForm.toRemote()).toDomain()
}