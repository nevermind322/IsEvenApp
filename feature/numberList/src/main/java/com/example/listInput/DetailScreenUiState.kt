package com.example.listInput

import com.example.model.IsEven
import com.example.model.NumberFact

sealed class DetailScreenUiState {

    data object Loading : DetailScreenUiState()

    class Success(val data : NumberFact) : DetailScreenUiState()

    class Error(val msg : String) : DetailScreenUiState()
}