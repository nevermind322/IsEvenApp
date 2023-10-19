package com.example.listInput

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
fun NumberListScreen() {
       NumberList()
}

@Composable
fun NumberList() {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        for (i in 0 .. 100) item { NumberCard(i) }
    }

}


@Composable
fun NumberCard(value:Int) {
    Card(modifier = Modifier.height(100.dp)) {
        Text(text = value.toString())
    }
}