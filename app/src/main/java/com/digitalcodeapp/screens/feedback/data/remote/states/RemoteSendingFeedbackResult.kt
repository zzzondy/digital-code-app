package com.digitalcodeapp.screens.feedback.data.remote.states

sealed class RemoteSendingFeedbackResult {

    data object Success : RemoteSendingFeedbackResult()

    data object Error : RemoteSendingFeedbackResult()
}
