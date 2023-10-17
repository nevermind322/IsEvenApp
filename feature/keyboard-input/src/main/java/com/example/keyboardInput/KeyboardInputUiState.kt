package com.example.keyboardInput

import com.example.model.IsEven

sealed class KeyboardInputUiState{
    data class Success(val data : IsEven) : KeyboardInputUiState()
    data class Error(val message: String) : KeyboardInputUiState()
    data object Loading : KeyboardInputUiState()
    data object Greeting : KeyboardInputUiState()
}