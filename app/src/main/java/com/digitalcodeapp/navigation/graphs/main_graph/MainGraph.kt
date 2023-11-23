package com.digitalcodeapp.navigation.graphs.main_graph

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.digitalcodeapp.common.ui.components.AnimationConstants
import com.digitalcodeapp.navigation.Graphs
import com.digitalcodeapp.screens.dictionary.presentation.DictionaryScreen
import com.digitalcodeapp.screens.main.MainScreen
import com.digitalcodeapp.screens.quiz.presentation.QuizScreen

fun NavGraphBuilder.registerMainGraph(navController: NavController) {
    navigation(
        startDestination = Screen.MainScreen.route,
        route = Graphs.MainGraph.route
    ) {

        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }

        composable(
            route = Screen.DictionaryScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = AnimationConstants.ENTER_ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = AnimationConstants.EXIT_ANIMATION_DURATION)
                )
            }
        ) {
            DictionaryScreen(navController = navController)
        }
        
        composable(
            route = Screen.QuizScreen.route,
            enterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(durationMillis = AnimationConstants.ENTER_ANIMATION_DURATION)
                )
            },
            popExitTransition = {
                slideOutOfContainer(
                    towards = AnimatedContentTransitionScope.SlideDirection.Right,
                    animationSpec = tween(durationMillis = AnimationConstants.EXIT_ANIMATION_DURATION)
                )
            }
        ) {
            QuizScreen(navController = navController)
        }
    }
}