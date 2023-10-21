package com.example.listInput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun NumberListScreen(onNumberClick: (Int) -> Unit) {
    NumberList(onNumberClick)
}

@Composable
fun NumberList(onNumberClick: (Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        for (i in 0..100) item { NumberCard(i, onNumberClick) }
    }

}


@Composable
fun NumberCard(value: Int, onNumberClick: (Int) -> Unit) {
    Card(modifier = Modifier
        .height(100.dp)
        .clickable { onNumberClick(value) }) {
        Text(text = value.toString())
    }
}