package com.digitalcodeapp.screens.main.states

sealed interface MainScreenAction {

    data object OnDictionarySectionClicked : MainScreenAction
}