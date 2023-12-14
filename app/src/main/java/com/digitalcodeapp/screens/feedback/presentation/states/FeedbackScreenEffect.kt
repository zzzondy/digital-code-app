package com.digitalcodeapp.screens.feedback.presentation.states

sealed interface FeedbackScreenEffect {

    data object NavigateBack : FeedbackScreenEffect

    data object ShowLoadingDialog : FeedbackScreenEffect

    data object HideLoadingDialog : FeedbackScreenEffect

    data object ShowSuccessDialog : FeedbackScreenEffect

    data object ShowErrorDialog : FeedbackScreenEffect
}