package com.digitalcodeapp.navigation.graphs.main_graph

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.digitalcodeapp.navigation.Graphs
import com.digitalcodeapp.screens.dictionary.presentation.DictionaryScreen
import com.digitalcodeapp.screens.main.MainScreen

fun NavGraphBuilder.registerMainGraph(navController: NavController) {
    navigation(
        startDestination = Screens.MainScreen.route,
        route = Graphs.MainGraph.route
    ) {

        composable(route = Screens.MainScreen.route) {
            MainScreen(navController = navController)
        }
        
        composable(route = Screens.DictionaryScreen.route) {
            DictionaryScreen(navController = navController)
        }
    }
}