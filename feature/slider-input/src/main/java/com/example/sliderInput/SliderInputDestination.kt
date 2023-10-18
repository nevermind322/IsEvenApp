package com.example.sliderInput

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SLIDER_INPUT_ROUTE = "SliderInput"

fun NavController.navigateToSliderInput(){
    navigate(SLIDER_INPUT_ROUTE)
}

fun NavGraphBuilder.sliderInputScreen(){
    composable(SLIDER_INPUT_ROUTE) { SliderInputScreen(vm = hiltViewModel())}
}