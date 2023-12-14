package com.digitalcodeapp.screens.feedback.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.common.dispatchers.DispatchersProvider
import com.digitalcodeapp.screens.feedback.domain.models.FeedbackForm
import com.digitalcodeapp.screens.feedback.domain.states.SendingFeedbackResult
import com.digitalcodeapp.screens.feedback.domain.use_cases.SendFeedbackUseCase
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenAction
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenEffect
import com.digitalcodeapp.screens.feedback.presentation.states.FeedbackScreenState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FeedbackScreenViewModel @Inject constructor(
    private val sendFeedbackUseCase: SendFeedbackUseCase,
    private val dispatchersProvider: DispatchersProvider,
) : ViewModel() {

    private val _effect = MutableSharedFlow<FeedbackScreenEffect>()
    val effect = _effect.asSharedFlow()


    private val _state = MutableStateFlow<FeedbackScreenState>(
        FeedbackScreenState.Content(
            message = "",
            name = "",
            isMessageError = false,
            isNameError = false
        )
    )
    val state = _state.asStateFlow()

    fun onAction(action: FeedbackScreenAction) {
        when (action) {
            is FeedbackScreenAction.OnBackButtonClicked -> onBackButtonClicked()

            is FeedbackScreenAction.OnSendButtonClicked -> onSendButtonClicked()

            is FeedbackScreenAction.OnMessageTyped -> onMessageTyped(action.message)

            is FeedbackScreenAction.OnNameTyped -> onNameTyped(action.name)
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            _effect.emit(FeedbackScreenEffect.NavigateBack)
        }
    }

    private fun onMessageTyped(message: String) {
        val state = _state.value as FeedbackScreenState.Content
        viewModelScope.launch {
            _state.update {
                state.copy(message = message, isMessageError = false,)
            }
        }
    }

    private fun onNameTyped(name: String) {
        val state = _state.value as FeedbackScreenState.Content
        viewModelScope.launch {
            _state.update {
                state.copy(name = name, isNameError = false,)
            }
        }
    }

    private fun onSendButtonClicked() {
        viewModelScope.launch(dispatchersProvider.io) {
            val state = _state.value as FeedbackScreenState.Content

            val isMessageError = state.message.isBlank()
            val isNameError = state.name.isBlank()

            if (isMessageError || isNameError) {
                _state.update {
                    state.copy(isMessageError = isMessageError, isNameError = isNameError)
                }
            } else {
                _effect.emit(FeedbackScreenEffect.ShowLoadingDialog)
                when (
                    sendFeedbackUseCase(
                        FeedbackForm(
                            message = state.message,
                            name = state.name,
                        )
                    )
                ) {
                    is SendingFeedbackResult.Success -> {
                        _effect.emit(FeedbackScreenEffect.HideLoadingDialog)
                        _effect.emit(FeedbackScreenEffect.ShowSuccessDialog)
                    }

                    is SendingFeedbackResult.Error -> {
                        _effect.emit(FeedbackScreenEffect.HideLoadingDialog)
                        _effect.emit(FeedbackScreenEffect.ShowErrorDialog)
                    }
                }
            }
        }
    }
}