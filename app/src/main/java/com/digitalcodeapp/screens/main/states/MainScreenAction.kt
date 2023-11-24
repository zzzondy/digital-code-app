package com.digitalcodeapp.screens.main.states

sealed interface MainScreenAction {

    data object OnDictionarySectionClicked : MainScreenAction

    data object OnFinancialScamSectionClicked : MainScreenAction

    data object OnPocketMoneySectionClicked : MainScreenAction

    data object OnFactsAboutRubleSectionClicked : MainScreenAction

    data object OnQuizSectionClicked : MainScreenAction
}