package com.digitalcodeapp.screens.feedback.presentation.states

sealed class FeedbackScreenAction {

    data object OnBackButtonClicked : FeedbackScreenAction()

    data object OnSendButtonClicked : FeedbackScreenAction()

    data class OnMessageTyped(val message: String) : FeedbackScreenAction()

    data class OnNameTyped(val name: String) : FeedbackScreenAction()
}
