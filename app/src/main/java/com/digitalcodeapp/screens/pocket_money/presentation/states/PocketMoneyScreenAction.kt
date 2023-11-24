package com.digitalcodeapp.screens.pocket_money.presentation.states

sealed interface PocketMoneyScreenAction {

    data object OnBackButtonClicked : PocketMoneyScreenAction
}