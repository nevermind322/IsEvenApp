package com.example.listInput

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val NUMBER_LIST_ROUTE = "numberList"

private const val DETAIL_SCREEN_ARG_NAME = "id"
internal const val DETAIL_SCREEN_ROUTE = "detailScreen/{$DETAIL_SCREEN_ARG_NAME}"

fun NavController.navigateToNumberListScreen() {
    navigate(NUMBER_LIST_ROUTE)
}

internal fun NavController.navigateToNumberDetailScreen(number: Int) {
    navigate("detailScreen/$number")
}

fun NavGraphBuilder.numberListScreen(navController: NavController) {
    composable(NUMBER_LIST_ROUTE) {
        NumberListScreen(onNumberClick = {
            navController.navigateToNumberDetailScreen(it)
        })
    }
    composable(
        DETAIL_SCREEN_ROUTE,
        arguments = listOf(navArgument(DETAIL_SCREEN_ARG_NAME) { type = NavType.IntType })
    ) {
        NumberDetailScreen(number = it.arguments?.getInt(DETAIL_SCREEN_ARG_NAME)!!, vm = hiltViewModel())
    }
}