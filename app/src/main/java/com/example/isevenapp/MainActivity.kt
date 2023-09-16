package com.example.isevenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.example.isevenapp.ui.theme.IsEvenAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsEvenAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    EvenChecker()
                }
            }
        }
    }
}

@Composable
fun EvenChecker(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") }

    Column {
        BasicTextField(value = text, onValueChange = { text = it })
        val n = text.toIntOrNull()
        when {
            n == null -> Text(text = "Not a number!!!")
            n % 2 == 0 -> Text(text = "It's even!")
            else -> Text(text = "It's odd((")
        }
    }
}