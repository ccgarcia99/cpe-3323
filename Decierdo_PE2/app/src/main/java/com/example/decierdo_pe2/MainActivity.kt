package com.example.decierdo_pe2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.decierdo_pe2.ui.theme.Decierdo_PE2Theme

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
            modifier = modifier.padding(50.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "From IBM",
                modifier = modifier.padding(16.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.sample_image),
                contentDescription = "Congratulations",
                modifier= Modifier
                    .padding(all = 10.dp)
                    .width(300.dp)
                    .height(200.dp)
            )
        }
    }
}

@Preview(
    name = "Card Preview",
    widthDp = 320,
    showBackground = true
)
@Composable
fun GreetingCardPreview(modifier: Modifier = Modifier) {
    Decierdo_PE2Theme {
        MyCard()
    }
}
