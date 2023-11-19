package com.digitalcodeapp.screens.dictionary.presentation.states

sealed class DictionaryScreenAction {

    data object OnBackArrowClicked : DictionaryScreenAction()

    data object OnRefreshButtonClicked : DictionaryScreenAction()

    data class OnEnteredNewQuery(val query: String) : DictionaryScreenAction()


}