package com.digitalcodeapp.screens.feedback.domain.use_cases

import com.digitalcodeapp.screens.feedback.domain.models.FeedbackForm
import com.digitalcodeapp.screens.feedback.domain.repository.FeedbackRepository
import com.digitalcodeapp.screens.feedback.domain.states.SendingFeedbackResult

class SendFeedbackUseCase(private val feedbackRepository: FeedbackRepository) {

    suspend operator fun invoke(feedbackForm: FeedbackForm): SendingFeedbackResult =
        feedbackRepository.sendFeedback(feedbackForm)
}