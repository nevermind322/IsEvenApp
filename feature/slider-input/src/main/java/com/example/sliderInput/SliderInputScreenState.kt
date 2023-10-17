package com.example.sliderInput

import com.example.model.IsEven

sealed class SliderInputScreenState {
    data class Success(val data : IsEven) : SliderInputScreenState()
    data class Error(val msg : String) : SliderInputScreenState()
    data object Loading : SliderInputScreenState()
    data object Greeting : SliderInputScreenState()
}