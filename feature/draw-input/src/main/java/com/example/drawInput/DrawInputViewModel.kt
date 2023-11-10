package com.example.drawInput

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RepoResult
import com.example.domain.ClassifyEvenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawInputViewModel @Inject constructor(private val useCase: ClassifyEvenUseCase) :
    ViewModel() {

    private val _state = MutableStateFlow<DrawInputScreenUiState>(DrawInputScreenUiState.Greeting)
    val state = _state.asStateFlow()

    fun classify(bitmap: Bitmap) {
        Log.d(TAG, "VM classify ")
        viewModelScope.launch {
            _state.update { DrawInputScreenUiState.Loading }
            val res = useCase(bitmap)
            Log.d(TAG, "got res ${res::class}")
            _state.update {
                when (res) {
                    is RepoResult.Success -> DrawInputScreenUiState.Success(res.data)
                    is RepoResult.Error -> DrawInputScreenUiState.Error(res.msg)
                }
            }
        }

    }
}

