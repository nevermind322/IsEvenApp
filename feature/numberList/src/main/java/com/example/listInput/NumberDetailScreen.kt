package com.example.listInput

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun NumberDetailScreen(number : Int ,vm   : NumberDetailViewModel = viewModel() ) {
    Text(text = number.toString())
}