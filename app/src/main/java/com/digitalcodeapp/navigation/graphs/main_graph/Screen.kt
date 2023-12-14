package com.digitalcodeapp.navigation.graphs.main_graph

sealed class Screen(val route: String) {

    data object MainScreen : Screen(route = "main_screen")

    data object DictionaryScreen : Screen(route = "dictionary_screen")

    data object FinancialScamScreen : Screen(route = "financial_scam_screen")

    data object PocketMoneyScreen : Screen(route = "pocket_money_screen")

    data object FactsAboutRubleScreen : Screen(route = "facts_about_ruble_screen")

    data object QuizScreen : Screen(route = "quiz_screen")

    data object FeedbackScreen : Screen(route = "feedback_screen")
}
