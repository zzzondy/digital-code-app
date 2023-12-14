package com.digitalcodeapp.screens.feedback.presentation.states

sealed class FeedbackScreenState {

    data class Content(
        val message: String,
        val name: String,
        val isMessageError: Boolean,
        val isNameError : Boolean,
    ) : FeedbackScreenState()
}
