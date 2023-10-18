package com.example.keyboardInput

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val KEYBOARD_INPUT_ROUTE = "keyboardInput"

fun NavController.navigateToKeyboardInput() {
    navigate(KEYBOARD_INPUT_ROUTE)
}

fun NavGraphBuilder.keyboardInputScreen() {
    composable(KEYBOARD_INPUT_ROUTE) { KeyboardInputScreen(vm = hiltViewModel()) }
}