package com.digitalcodeapp.screens.pocket_money.presentation.states

sealed interface PocketMoneyScreenEffect {

    data object NavigateBack : PocketMoneyScreenEffect
}