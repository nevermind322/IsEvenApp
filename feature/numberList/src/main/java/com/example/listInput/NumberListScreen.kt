package com.example.listInput

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NumberListScreen(onNumberClick: (Int) -> Unit) {
    NumberList(onNumberClick)
}

@Composable
fun NumberList(onNumberClick: (Int) -> Unit) {
    LazyVerticalGrid(columns = GridCells.Fixed(2)) {
        for (i in 0..100_000)
            item(key = i) {
                NumberCard(
                    i,
                    modifier = Modifier
                        .height(100.dp)
                        .clickable { onNumberClick(i) }
                )
            }
    }
}


@Composable
fun NumberCard(value: Int, modifier: Modifier) {
    Card(modifier = modifier) {
        Text(text = value.toString())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CardPreview() {
    NumberCard(value = 322, modifier = Modifier.height(100.dp).fillMaxWidth(0.5f))
}

@Preview
@Composable
fun NumberListPreview() {
    NumberList(onNumberClick = {})
}