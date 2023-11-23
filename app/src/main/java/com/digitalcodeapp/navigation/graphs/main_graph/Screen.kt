package com.digitalcodeapp.navigation.graphs.main_graph

sealed class Screen(val route: String) {

    data object MainScreen : Screen(route = "main_screen")

    data object DictionaryScreen : Screen(route = "dictionary_screen")

    data object QuizScreen : Screen(route = "quiz_screen")
}
