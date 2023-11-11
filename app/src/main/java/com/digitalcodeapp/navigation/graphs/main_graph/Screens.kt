package com.digitalcodeapp.navigation.graphs.main_graph

sealed class Screens(val route: String) {

    data object MainScreen : Screens(route = "main_screen")

    data object DictionaryScreen : Screens(route = "dictionary_screen")
}
