package com.example.isevenapp

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.isevenapp.ui.theme.IsEvenAppTheme
import com.example.keyboardInput.KEYBOARD_INPUT_ROUTE
import com.example.keyboardInput.keyboardInputScreen
import com.example.keyboardInput.navigateToKeyboardInput
import com.example.listInput.navigateToNumberListScreen
import com.example.listInput.numberListScreen
import com.example.sliderInput.navigateToSliderInput
import com.example.sliderInput.sliderInputScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IsEvenAppTheme {
                IsEvenApp()
            }
        }
    }
}



@Composable
fun IsEvenApp(navController: NavHostController = rememberNavController()) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column {
            IsEvenNavHost(navController = navController)
            Row {
                Button(onClick = { navController.navigateToKeyboardInput() }) { Text(text = "Keyboard") }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = { navController.navigateToSliderInput() }) { Text(text = "Slider") }
                Spacer(modifier = Modifier.width(16.dp))
                Button(onClick = {navController.navigateToNumberListScreen()}) {Text(text = "List")}
            }
        }
    }
}


@Composable
fun IsEvenNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = KEYBOARD_INPUT_ROUTE) {
        keyboardInputScreen()
        sliderInputScreen()
        numberListScreen()
    }
}