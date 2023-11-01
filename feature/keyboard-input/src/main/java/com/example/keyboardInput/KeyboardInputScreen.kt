package com.example.keyboardInput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KeyboardInputScreen(vm: KeyboardInputViewModel = viewModel()) {
    var text by rememberSaveable { mutableStateOf("") }

    val state by vm.state.collectAsState()

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        TextField(value = text, onValueChange = { text = it; vm.isEven(text) })
        when(state) {
            is KeyboardInputUiState.Success -> Text(text = if ((state as KeyboardInputUiState.Success).data.isEven) "Even!)" else "Odd(")
            is KeyboardInputUiState.Error -> Text(text = (state as KeyboardInputUiState.Error).message)
            is KeyboardInputUiState.Loading -> Text(text = "Loading")
            is KeyboardInputUiState.Greeting -> Text(text = "Hello! Enter a  number")
        }
        
    }
}