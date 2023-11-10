package com.example.drawInput

import com.example.domain.NumberIsEven

sealed class DrawInputScreenUiState {

    data class Success(val data: NumberIsEven) : DrawInputScreenUiState()

    data class Error(val msg: String) : DrawInputScreenUiState()

    data object Loading : DrawInputScreenUiState()

    data object Greeting : DrawInputScreenUiState()
}