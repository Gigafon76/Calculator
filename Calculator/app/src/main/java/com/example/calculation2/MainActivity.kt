package com.example.calculation2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calculation2.ui.theme.Calculation2Theme
import com.example.calculation2.ui.theme.screens.home.HomeScreen
import com.example.calculation2.ui.theme.screens.home.HomeViewModel

class MainActivity : ComponentActivity() {
    val viewModel by viewModels <HomeViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Calculation2Theme {
               HomeScreen(viewModel = viewModel)
            }
        }
    }
}
