package com.example.isevenapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.example.isevenapp.ui.theme.IsEvenAppTheme
import com.example.keyboardInput.KEYBOARD_INPUT_ROUTE
import com.example.keyboardInput.keyboardInputScreen
import com.example.keyboardInput.navigateToKeyboardInput
import com.example.sliderInput.navigateToSliderInput
import com.example.sliderInput.sliderInputScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

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


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsEvenApp(navController: NavHostController = rememberNavController()) {
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val map = mapOf<String, () -> Unit>(
        "Keyboard" to {
            navController.navigateToKeyboardInput()
            scope.launch { drawerState.close() }
        },
        "Slider " to {
            navController.navigateToSliderInput()
            scope.launch { drawerState.close() }
        }
    )

    ModalNavigationDrawer(
        drawerContent = { IsEvenDrawerSheet(clickActions = map) },
        drawerState = drawerState
    ) {
        Scaffold(topBar = {
            IsEvenTopAppBar {
                scope.launch {
                    if (drawerState.isOpen) drawerState.close() else drawerState.open()
                }
            }
        }) {
            Column(modifier = Modifier.padding(it)) {
                IsEvenNavHost(navController = navController)
            }

        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsEvenTopAppBar(onNavClick: () -> Unit) {

    TopAppBar(title = { Text(text = "IsEven?") }, navigationIcon = {
        Icon(
            modifier = Modifier.clickable { onNavClick() },
            imageVector = Icons.Default.Menu,
            contentDescription = ""
        )
    })

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IsEvenDrawerSheet(clickActions: Map<String, () -> Unit>) {

    ModalDrawerSheet {
        Column {
            for ((screen, action) in clickActions) {
                Button(onClick = action) { Text(text = screen) }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }

}

@Composable
fun IsEvenNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = KEYBOARD_INPUT_ROUTE) {
        keyboardInputScreen()
        sliderInputScreen()
    }
}
