package com.example.listInput

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.model.NumberFact

@Composable
fun NumberDetailScreen(number: Int, vm: NumberDetailViewModel = viewModel()) {

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.getFact(number)
    }

    when (state) {
        is DetailScreenUiState.Loading -> CircularProgressIndicator()
        is DetailScreenUiState.Error -> Text(text = (state as DetailScreenUiState.Error).msg)
        is DetailScreenUiState.Success -> NumberFact(fact = (state as DetailScreenUiState.Success).data)
    }
}


@Composable
fun NumberFact(fact: NumberFact) {

    Column {
        Text(text = fact.number.toString())
        Text(text = fact.fact)
    }

}