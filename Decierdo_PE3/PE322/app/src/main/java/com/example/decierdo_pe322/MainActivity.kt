/*Name: Christian Clyde G. Decierdo
  Activity: PE 3.2.2 - Quote of the Day
  Course: CPE 3323
  Date: June 25, 2024
* */

package com.example.decierdo_pe322

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.decierdo_pe322.ui.theme.Decierdo_PE322Theme

class MainActivity : ComponentActivity() {
    private var modifier = Modifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Decierdo_PE322Theme(
                dynamicColor = false
            ) {
                Scaffold(
                    // TODO: Add a top bar and a bottom bar
                    modifier = modifier.fillMaxSize()
                ) { innerPadding ->
                    RootComposable(
                        modifier = modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RootComposable(modifier: Modifier = Modifier) {
    Surface(
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

        }
    }
}

@Composable
fun ImageAndTextView() {
    Column {

    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    Decierdo_PE322Theme(
        dynamicColor = false
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            innerPadding -> RootComposable(modifier = Modifier.padding(innerPadding))
        }
    }
}