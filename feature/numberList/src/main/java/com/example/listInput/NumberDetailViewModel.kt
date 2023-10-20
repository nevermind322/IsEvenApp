package com.example.listInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.EvenRepository
import com.example.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberDetailViewModel @Inject constructor(private val evenRepo: EvenRepository) :
    ViewModel() {


    private var job: Job? = null
    private val _state = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Loading)

    val state = _state.asStateFlow()

    fun isEven(number: Int) {
        viewModelScope.launch {
            job?.cancel()
            job = viewModelScope.launch {
                val res = evenRepo.isEven(number)
                _state.update {
                    when (res) {
                        is ApiResult.Success -> DetailScreenUiState.Success(res.data)
                        is ApiResult.Error -> DetailScreenUiState.Error(res.msg)
                        is ApiResult.Exception -> DetailScreenUiState.Error(
                            res.e.message ?: "Unknown error!"
                        )

                    }
                }

            }
        }
    }
}

