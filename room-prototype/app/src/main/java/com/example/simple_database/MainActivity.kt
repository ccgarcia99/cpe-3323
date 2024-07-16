package com.example.simple_database

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simple_database.ui.theme.SimpledatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        
        val modifier: Modifier = Modifier
        setContent {
            MainScreen(modifier = modifier)
        }
    }
}

@Composable
fun MainScreen(modifier: Modifier) {
    
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewMainScreen(modifier: Modifier = Modifier) {
    MainScreen(modifier = modifier)
}