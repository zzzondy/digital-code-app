package com.digitalcodeapp.screens.dictionary.presentation.states

sealed interface DictionaryScreenEffect {

    data object NavigateBack : DictionaryScreenEffect

    data object RefreshData : DictionaryScreenEffect
}