package com.example.listInput

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.fact.NumberFactRepository
import com.example.model.NumberFact
import com.example.data.RepoResult
import com.example.domain.EvenFactUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NumberDetailViewModel @Inject constructor(private val useCase: EvenFactUseCase) :
    ViewModel() {


    private var job: Job? = null
    private val _state = MutableStateFlow<DetailScreenUiState>(DetailScreenUiState.Loading)

    val state = _state.asStateFlow()

    fun getFact(number: Int, factType: NumberFact.FactType = NumberFact.FactType.MATH) {
        viewModelScope.launch {
            job?.cancel()
            job = viewModelScope.launch {
                val res = useCase(number, factType)
                _state.update {
                    when (res) {
                        is RepoResult.Success -> DetailScreenUiState.Success(res.data)
                        is RepoResult.Error -> DetailScreenUiState.Error(res.msg)
                    }
                }
            }
        }
    }
}

