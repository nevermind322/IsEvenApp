package com.example.keyboardInput

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment


@Composable
fun KeyboardInputScreen() {
    var text by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        BasicTextField(value = text, onValueChange = { text = it })
        val n = text.toIntOrNull()
        when {
            n == null -> Text(text = "Not a number!!!")
            n % 2 == 0 -> Text(text = "It's even!")
            else -> Text(text = "It's odd((")
        }
    }
}