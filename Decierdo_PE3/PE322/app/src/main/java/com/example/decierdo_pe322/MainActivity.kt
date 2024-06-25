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
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        color = MaterialTheme.colorScheme.background,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ImageAndTextView(modifier = modifier)
            Spacer(modifier = Modifier.height(20.dp))
            Randomizer(modifier = modifier)
        }
    }
}

@Composable
fun ImageAndTextView(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally, // Center content horizontally
        modifier = modifier.padding(16.dp) // Add padding to the Column
    ) {
        Image(
            painter = painterResource(id = R.drawable._681495279040607),
            contentDescription = null,
            modifier = Modifier
                .height(200.dp)
                .width(300.dp)
                .align(Alignment.CenterHorizontally) // Center the image horizontally
                .padding(bottom = 25.dp) // Add padding below the image
        )
        Text(
            text = """
            "This is a sample multiline text
            quote. You can put anything here to your
            hearts desire."
            """.trimIndent(),
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.Left, // Center the text
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Text(
            text = "- J. Author",
            fontWeight = FontWeight.Light,
            modifier = Modifier.align(Alignment.End).padding(top = 8.dp) // Add padding above the author text
        )
    }
}


@Composable
fun Randomizer(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        OutlinedButton(
            onClick = { /*TODO*/ },
            enabled = true,
            border = BorderStroke(1.dp, color = Color.Black)
        ) {
            Text(
                text = "Get New Quote!",
                fontWeight = FontWeight.Light,
                fontSize = 25.sp,
                color = Color.Black
            )
        }
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