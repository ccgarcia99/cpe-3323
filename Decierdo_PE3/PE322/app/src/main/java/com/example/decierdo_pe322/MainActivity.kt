package com.example.decierdo_pe322

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdo_pe322.ui.theme.Decierdo_PE322Theme
import com.example.decierdo_pe322.ui.theme.WinterBlueLightPrimaryContainer

data class Quote(val text: String, val author: String)

val quotes = listOf(
    Quote("Life is like riding a bicycle. To keep your balance you must keep moving.", "Albert Einstein"),
    Quote("The best way to predict the future is to invent it.", "Alan Kay"),
    Quote("Life is 10% what happens to us and 90% how we react to it.", "Charles R. Swindoll"),
    Quote("The only way to do great work is to love what you do.", "Steve Jobs"),
    Quote("If you can dream it, you can achieve it.", "Zig Ziglar"),
    Quote("The best revenge is massive success.", "Frank Sinatra"),
    Quote("Your time is limited, don’t waste it living someone else’s life.", "Steve Jobs"),
    Quote("Whether you think you can or you think you can’t, you’re right.", "Henry Ford"),
    Quote("The only limit to our realization of tomorrow is our doubts of today.", "Franklin D. Roosevelt"),
    Quote("Don’t watch the clock; do what it does. Keep going.", "Sam Levenson")
)

class MainActivity : ComponentActivity() {
    private val modifier = Modifier

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Decierdo_PE322Theme(
                dynamicColor = false,
                darkTheme = false
            ) {
                RootComposable(modifier)
            }
        }
    }
}

@Composable
fun RootComposable(
    modifier: Modifier = Modifier
) {
    // variable declarations with our data class property
    var currentQuote by remember { mutableStateOf(quotes.random()) }
    var previousQuote by remember { mutableStateOf<Quote?>(null)}

    Surface(
        modifier = modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        ConstraintLayout(
            modifier = modifier
                .fillMaxSize()
                .padding(36.dp) // Added padding to ensure margins
        ) {
            // Create reference values for our composables
            val (image, quoteText, authorText, button) = createRefs()

            Image(
                painter = painterResource(id = R.drawable._684267427813678),
                contentDescription = null,
                modifier = modifier
                    .fillMaxWidth()
                    .aspectRatio(16f / 9f)
                    .constrainAs(image) {
                        top.linkTo(parent.top, margin = 60.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
            )
            Text(
                text = currentQuote.text,
                modifier = modifier
                    .constrainAs(quoteText) {
                        top.linkTo(image.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(20.dp),
                textAlign = TextAlign.Left, // Align the text to the left
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                fontFamily = FontFamily.Serif
            )
            Text(
                text = "- ${currentQuote.author}",
                modifier = modifier
                    .constrainAs(authorText) {
                        top.linkTo(quoteText.bottom, margin = 8.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                    },
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            )
            OutlinedButton(
                modifier = modifier
                    .fillMaxWidth()
                    .constrainAs(button) {
                        bottom.linkTo(parent.bottom, margin = 40.dp)
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                    },
                onClick = {
                    var newQuote: Quote
                    do {
                        newQuote = quotes.random()
                    } while (newQuote == currentQuote)
                    previousQuote = currentQuote
                    currentQuote = newQuote
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = WinterBlueLightPrimaryContainer
                )
            ) {
                Text(
                    text = "Get New Quote",
                    color = Color.Black
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
fun Preview() {
    val modifier = Modifier
    Decierdo_PE322Theme {
        RootComposable(modifier)
    }
}
