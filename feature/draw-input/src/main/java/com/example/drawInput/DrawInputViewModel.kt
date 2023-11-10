package com.example.drawInput

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.RepoResult
import com.example.domain.ClassifyEvenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DrawInputViewModel @Inject constructor(private val useCase: ClassifyEvenUseCase) :
    ViewModel() {

    val state = MutableStateFlow(-1)

    fun classify(bitmap: Bitmap) {
        Log.d(TAG, "VM classify ")

        viewModelScope.launch {
            val res = useCase(bitmap)
            Log.d(TAG, "got res ${res::class}")
            state.update {
                when (res) {
                    is RepoResult.Success -> res.data.number
                    is RepoResult.Error -> {
                        Log.d(TAG, "got error ${res.msg}")

                        -3
                    }
                }
            }
        }

    }
}

