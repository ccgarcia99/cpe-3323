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
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decierdo_pe322.ui.theme.Decierdo_PE322Theme
import com.example.decierdo_pe322.ui.theme.Presets


class MainActivity : ComponentActivity() {
    private val modifier = Modifier
    private val presets = Presets()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootComposable(modifier, presets)
        }
    }
}

@Composable
fun RootComposable(modifier: Modifier = Modifier, presets: Presets) {
    Decierdo_PE322Theme(
        dynamicColor = false
    ) {
        Scaffold(
            topBar = {presets.topAppBarPresets("Quote of the Day")},
            containerColor = MaterialTheme.colorScheme.surface
        ) {
            innerPadding ->
            Column(
                modifier = modifier.padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box {
                    Surface(
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable._692970553060802),
                            contentDescription = null,
                            modifier = modifier.padding(10.dp)
                        )
                    }
                }
                Spacer(modifier = modifier.height(10.dp))
                Text(
                    text = """
                        "I shit and fart"
                        "I shit and fart"
                        "I shit and fart"
                        "I shit and fart"
                        "I shit and fart"
                        "I shit and fart"
                    """.trimIndent(),
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Italic,
                    fontSize = 24.sp,
                    modifier = modifier
                        .align(Alignment.Start)
                        .padding(start = 10.dp)
                        .padding(bottom = 10.dp)
                )
                Text(
                    text = """
                        - an Inspirational Author
                    """.trimIndent(),
                    fontFamily = FontFamily.Serif,
                    fontStyle = FontStyle.Normal,
                    fontSize = 18.sp,
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(end = 10.dp)
                        .padding(top = 10.dp)
                )
            }
        }
    }
}




@Preview(
    showSystemUi = true,
    showBackground = true
)
@Composable
fun Preview(){
    val modifier = Modifier
    val presets = Presets()
    RootComposable(modifier, presets)
}



