package com.digitalcodeapp.screens.financial_scam.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.screens.financial_scam.presentation.states.FinancialScamScreenAction
import com.digitalcodeapp.screens.financial_scam.presentation.states.FinancialScamScreenEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FinancialScamScreenViewModel @Inject constructor() : ViewModel() {

    private val _effect = MutableSharedFlow<FinancialScamScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun onAction(action: FinancialScamScreenAction) {
        when (action) {
            FinancialScamScreenAction.OnBackButtonClicked -> onBackButtonClicked()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            _effect.emit(FinancialScamScreenEffect.NavigateBack)
        }
    }
}