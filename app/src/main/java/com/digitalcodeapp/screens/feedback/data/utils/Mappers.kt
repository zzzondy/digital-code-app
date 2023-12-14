package com.digitalcodeapp.screens.feedback.data.utils

import com.digitalcodeapp.screens.feedback.data.remote.models.RemoteFeedbackForm
import com.digitalcodeapp.screens.feedback.data.remote.states.RemoteSendingFeedbackResult
import com.digitalcodeapp.screens.feedback.domain.models.FeedbackForm
import com.digitalcodeapp.screens.feedback.domain.states.SendingFeedbackResult

fun RemoteSendingFeedbackResult.toDomain(): SendingFeedbackResult =
    when (this) {
        is RemoteSendingFeedbackResult.Success -> SendingFeedbackResult.Success

        is RemoteSendingFeedbackResult.Error -> SendingFeedbackResult.Error
    }

fun FeedbackForm.toRemote(): RemoteFeedbackForm =
    RemoteFeedbackForm(
        message, name
    )