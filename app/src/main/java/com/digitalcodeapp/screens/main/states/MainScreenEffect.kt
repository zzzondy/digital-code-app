package com.digitalcodeapp.screens.main.states

sealed interface MainScreenEffect {

    data object NavigateToDictionaryScreen : MainScreenEffect

    data object NavigateToQuizScreen : MainScreenEffect
}