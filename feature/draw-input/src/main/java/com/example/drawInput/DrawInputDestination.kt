package com.example.drawInput

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val DRAW_INPUT_ROUTE = "drawInput"

fun NavController.navigateToDrawInput() {
    navigate(DRAW_INPUT_ROUTE)
}

fun NavGraphBuilder.drawInputScreen() {
    composable(DRAW_INPUT_ROUTE) { DrawInputScreen() }
}
