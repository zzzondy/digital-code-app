package com.digitalcodeapp.screens.main.states

sealed interface MainScreenEffect {

    data object NavigateToDictionaryScreen : MainScreenEffect

    data object NavigateToFinancialScamScreen : MainScreenEffect

    data object NavigateToPocketMoneyScreen : MainScreenEffect

    data object NavigateToFactsAboutRubleScreen : MainScreenEffect

    data object NavigateToQuizScreen : MainScreenEffect

    data object NavigateToFeedbackScreen : MainScreenEffect
}