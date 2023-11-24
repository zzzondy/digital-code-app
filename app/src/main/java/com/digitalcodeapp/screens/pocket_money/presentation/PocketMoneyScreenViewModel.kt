package com.digitalcodeapp.screens.pocket_money.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.screens.pocket_money.presentation.states.PocketMoneyScreenAction
import com.digitalcodeapp.screens.pocket_money.presentation.states.PocketMoneyScreenEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PocketMoneyScreenViewModel @Inject constructor() : ViewModel() {

    private val _effect = MutableSharedFlow<PocketMoneyScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun onAction(action: PocketMoneyScreenAction) {
        when (action) {
            PocketMoneyScreenAction.OnBackButtonClicked -> onBackButtonClicked()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            _effect.emit(PocketMoneyScreenEffect.NavigateBack)
        }
    }
}