package com.example.listInput

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val NUMBER_LIST_ROUTE = "numberList"

fun NavController.navigateToNumberListScreen(){
    navigate(NUMBER_LIST_ROUTE)
}

fun NavGraphBuilder.numberListScreen() {
    composable(NUMBER_LIST_ROUTE) { NumberListScreen()}
}