package com.example.listInput

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.domain.FactWithEven
import com.example.model.NumberFact

@Composable
fun NumberDetailScreen(number: Int, vm: NumberDetailViewModel = viewModel()) {

    val state by vm.state.collectAsState()

    LaunchedEffect(Unit) {
        vm.getFact(number, NumberFact.FactType.getRandomTypeExceptDate())
    }

    when (state) {
        is DetailScreenUiState.Loading -> CircularProgressIndicator()
        is DetailScreenUiState.Error -> Text(text = (state as DetailScreenUiState.Error).msg)
        is DetailScreenUiState.Success -> NumberFact(fact = (state as DetailScreenUiState.Success).data)
    }
}


@Composable
fun NumberFact(fact: FactWithEven) {

    Column {
        Text(text = fact.fact.number.toString())
        Text(text = "Even: ${if (fact.even.isEven) "yes" else "no"}")
        Text(text = fact.fact.fact)
    }

}