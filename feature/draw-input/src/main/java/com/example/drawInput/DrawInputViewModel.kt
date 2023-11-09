package com.example.drawInput

import androidx.lifecycle.ViewModel
import com.example.domain.ClassifyEvenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DrawInputViewModel @Inject constructor( private val  useCase : ClassifyEvenUseCase) : ViewModel() {



}