package com.digitalcodeapp.screens.feedback.domain.states

sealed class SendingFeedbackResult {

    data object Success : SendingFeedbackResult()

    data object Error : SendingFeedbackResult()
}
