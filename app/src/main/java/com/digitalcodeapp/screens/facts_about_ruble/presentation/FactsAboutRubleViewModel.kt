package com.digitalcodeapp.screens.facts_about_ruble.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.screens.facts_about_ruble.presentation.states.FactsAboutRubleAction
import com.digitalcodeapp.screens.facts_about_ruble.presentation.states.FactsAboutRubleEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FactsAboutRubleViewModel @Inject constructor() : ViewModel() {

    private val _effect = MutableSharedFlow<FactsAboutRubleEffect>()
    val effect = _effect.asSharedFlow()

    fun onAction(action: FactsAboutRubleAction) {
        when (action) {
            FactsAboutRubleAction.OnBackButtonClicked -> onBackButtonClicked()
        }
    }

    private fun onBackButtonClicked() {
        viewModelScope.launch {
            _effect.emit(FactsAboutRubleEffect.NavigateBack)
        }
    }
}