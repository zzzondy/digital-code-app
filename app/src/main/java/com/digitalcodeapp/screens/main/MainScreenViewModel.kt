package com.digitalcodeapp.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digitalcodeapp.screens.main.states.MainScreenAction
import com.digitalcodeapp.screens.main.states.MainScreenEffect
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel() {

    private val _effect = MutableSharedFlow<MainScreenEffect>()
    val effect = _effect.asSharedFlow()

    fun onAction(action: MainScreenAction) {
        when (action) {
            MainScreenAction.OnDictionarySectionClicked -> onDictionarySectionClicked()
        }
    }

    private fun onDictionarySectionClicked() {
        viewModelScope.launch {
            _effect.emit(MainScreenEffect.NavigateToDictionaryScreen)
        }
    }
}