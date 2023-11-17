package com.digitalcodeapp.screens.dictionary.presentation.states

sealed interface DictionaryScreenAction {

    data object OnBackArrowClicked : DictionaryScreenAction

    data object OnRefreshButtonClicked : DictionaryScreenAction
}