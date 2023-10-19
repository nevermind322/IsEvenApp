package com.example.sliderInput

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.IsEven

internal const val MAX = 100

internal fun IsEven.toStr() = if (isEven) "Even!" else "Odd("

@Composable
fun SliderInputScreen(vm : SliderInputViewModel = viewModel()) {

    var value by rememberSaveable {
        mutableFloatStateOf(0.0f)
    }

    val state by vm.state.collectAsState()

    Column(modifier = Modifier.padding(16.dp)) {
        Slider(value = value, onValueChange = { value = it }, steps = MAX - 1, valueRange = 0f..MAX.toFloat())
        Text(text = value.toInt().toString())
        Button(onClick = { vm.isEven(value.toInt())}) { Text("Press to know!") }

        when(state) {
            is SliderInputScreenState.Error ->   Text(text = (state as SliderInputScreenState.Error).msg)
            is SliderInputScreenState.Success -> Text(text = (state as SliderInputScreenState.Success).data.toStr())
            is SliderInputScreenState.Loading -> CircularProgressIndicator()
            is SliderInputScreenState.Greeting -> Text(text = "Hello, choose number")
        }

    }
}

