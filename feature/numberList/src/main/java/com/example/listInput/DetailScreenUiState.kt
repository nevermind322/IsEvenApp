package com.example.listInput

import com.example.model.IsEven

sealed class DetailScreenUiState {

    data object Loading : DetailScreenUiState()

    class Success(val data : IsEven) : DetailScreenUiState()

    class Error(val msg : String) : DetailScreenUiState()
}