/*
* Name:     Christian Clyde G. Decierdo
* Title:    PE 2
* */
package com.example.decierdo_pe2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.decierdo_pe2.ui.theme.Decierdo_PE2Theme
import com.example.decierdo_pe2.ui.theme.myBlack

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Decierdo_PE2Theme {
                MyCard()
            }
        }
    }
}

@Composable
fun MyCard(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.primary
    ) {
        Column(
            modifier = modifier.padding(
                start = 20.dp,
                end = 20.dp,
                top = 50.dp,
                bottom = 25.dp
            ),
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.Start
        ) {
            //TODO: Implement custom function that only returns the ff.
            Text(
                text = "Hello Christian,",
                fontFamily = FontFamily.Monospace,
                fontWeight = FontWeight.SemiBold,
                fontStyle = FontStyle.Normal,
                color = myBlack,
                modifier = modifier.padding(top = 25.dp, bottom = 5.dp),
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.labelLarge
            )
            MyImageBox(modifier)
            /*TODO: Implement the text body with the following:

            * */
        }
    }
}

@Composable
private fun MyImageBox(modifier: Modifier) {
    Card(
        modifier = modifier.border(
            width = 3.dp,
            color = myBlack,
            shape = RoundedCornerShape(4.dp)
            // explicitly declaring no rounded corners, just cuz
        )
    ) {
        Image(
            painter = painterResource(id = R.drawable.sample_image),
            contentDescription = "Congratulations",
        )
    }
}

@Preview(
    name = "Card Preview",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingCardPreview(modifier: Modifier = Modifier) {
    Decierdo_PE2Theme {
        MyCard()
    }
}
