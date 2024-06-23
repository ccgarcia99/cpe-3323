package com.example.compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose.ui.theme.ComposeTheme
import com.example.compose.ui.theme.callIcon
import com.example.compose.ui.theme.emailIcon
import com.example.compose.ui.theme.socMedIcon

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeTheme {
                // We've already passed a modifier parameter as a default value
                MyGreetingCard()
            }
        }
    }
}

@Composable
fun MyGreetingCard(modifier: Modifier = Modifier){
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Surface(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Column(
                modifier = modifier
                .padding(all = 20.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                IDandPicture(modifier = modifier)
                ContactDetails() // modifier already a default parameter
            }
        }
    }
}

@Composable
private fun IDandPicture(modifier: Modifier) {
    Column {
        Image(
            painter = painterResource(id = R.drawable.ibm_void),
            contentDescription = null,
            modifier = modifier
                .height(190.dp)
                .width(250.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(
            text = "Christian Clyde G. Decierdo",
            fontSize = 22.sp,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = "Software Engineer",
            fontSize = 20.sp,
            fontFamily = FontFamily.SansSerif,
            fontWeight = FontWeight.Light,
            textAlign = TextAlign.Start,
            modifier = modifier
                .padding(
                    top = 5.dp,
                    bottom = 150.dp
                )
                .align(alignment = Alignment.CenterHorizontally)
        )
    }
}

@Composable
fun ContactDetails() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start
    ) {
        SubDetails(icon = callIcon, details = "+63 123 456 7890")
        SubDetails(icon = emailIcon, details = "sample@email.com")
        SubDetails(icon = socMedIcon, details = "@ccgarcia99")
    }
}

@Composable
fun SubDetails(
    modifier: Modifier = Modifier,
    details: String = "",
    icon: ImageVector
) {
    Row(
        modifier = modifier
            .padding(all = 4.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = modifier.padding(end = 10.dp),
        )
        Text(
            text = details,
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.Light
        )
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    name = "DarkMode"
)
@Composable
fun GreetingPreview() {
    ComposeTheme(darkTheme = true) { // force dark mode
        MyGreetingCard()
    }
}