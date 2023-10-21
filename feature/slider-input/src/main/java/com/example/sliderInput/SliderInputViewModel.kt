package com.example.sliderInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.EvenRepository
import com.example.data.RepoResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SliderInputViewModel @Inject constructor(private val repo: EvenRepository) : ViewModel() {

    private val job: Job? = null

    private val _state = MutableStateFlow<SliderInputScreenState>(SliderInputScreenState.Greeting)
    val state = _state.asStateFlow()
    fun isEven(number: Int) {
        job?.cancel()
        viewModelScope.launch {
            _state.update { SliderInputScreenState.Loading }
            val res = repo.isEven(number)
            _state.update {
                when (res) {
                    is RepoResult.Success -> SliderInputScreenState.Success(res.data)
                    is RepoResult.Error -> SliderInputScreenState.Error(res.msg)
                }
            }
        }
    }

}