package com.example.keyboardInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.even.EvenRepository
import com.example.data.RepoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class KeyboardInputViewModel @Inject constructor(private val evenRepo: EvenRepository) :
    ViewModel() {

    private var job: Job? = null

    private val _state = MutableStateFlow<KeyboardInputUiState>(KeyboardInputUiState.Greeting)

    val state = _state.asStateFlow()

    fun isEven(number: String) {
        job?.cancel()
        job = viewModelScope.launch {
            if (number == "") _state.update { KeyboardInputUiState.Greeting }
            else {
                _state.update { KeyboardInputUiState.Loading }
                val n = number.toIntOrNull()

                if (n == null) {
                    _state.update { KeyboardInputUiState.Error("Not a number!") }
                } else {
                    val res = evenRepo.isEven(n)
                    _state.update {
                        when (res) {
                            is RepoResult.Success -> KeyboardInputUiState.Success(res.data)
                            is RepoResult.Error -> KeyboardInputUiState.Error(res.msg)
                        }
                    }
                }
            }
        }
    }

}