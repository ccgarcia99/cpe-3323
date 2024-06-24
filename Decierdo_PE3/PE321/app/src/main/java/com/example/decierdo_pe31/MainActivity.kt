package com.example.decierdo_pe31

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.decierdo_pe31.ui.theme.Decierdo_PE31Theme
import com.example.decierdo_pe31.ui.theme.defaultTextFieldColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val modifier = Modifier // Invoking a Modifier object to be passed on to the root composable
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Decierdo_PE31Theme(
                dynamicColor = false // force adopt our themes
            ) {
                Scaffold(modifier.fillMaxSize()) {
                    innerPadding -> Surface(
                        modifier.padding(innerPadding),
                        color = MaterialTheme.colorScheme.primary
                    ) {
                        MyApp()
                    }
                }
            }
        }
    }
}


// Root composable function. Build UI here
@Composable
fun MyApp(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        /*TODO:
         1. Add a drop-down menu for the list of server names(Optional)
         2, Add a text field for order cost - DONE
         3. 3 radio buttons in a row for different tipping % - DONE
         4. Slider button to round up total cost
         5. Implement program logic
         6. Make UI adjustments
        * */
        BillField()
        RadioRowField()
        RoundUp()
    }
}

@Composable
fun RoundUp(modifier: Modifier = Modifier) {
    var toggled by remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = modifier
                .padding(bottom = 10.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Switch(
                checked = toggled,
                onCheckedChange = {toggled = it}
            )
            Spacer(modifier = modifier.width(8.dp))
            Text(
                text = "Round up tip",
                fontFamily = FontFamily.Serif,
            )
        }
    }

}

@Composable
fun RadioRowField(modifier: Modifier = Modifier) {
    val percentages = arrayOf("10%", "15%", "20%")
    var selectedPercentage by remember { mutableStateOf("") }
    Column {
        Text(
            text = "Select tip",
            fontFamily = FontFamily.Serif
        )
        Row(
            modifier = modifier
                .padding(bottom = 10.dp)
                .padding(horizontal = 10.dp),
            horizontalArrangement = Arrangement.Start,
        ) {
            for (item in percentages) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    RadioButton(
                        selected = (item == selectedPercentage),
                        onClick = { selectedPercentage = item },
                    )
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between each radio button and text pair
                }
            }
        }
    }
}


@Composable
fun BillField(modifier: Modifier = Modifier) {
    var text by remember { mutableStateOf("") } // Variable to be used in the text field composable

    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = {
            Text(
                "Enter bill total",
                fontFamily = FontFamily.Serif
            )
        },
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        colors = defaultTextFieldColors(),
        shape = RoundedCornerShape(10.dp)
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    val modifier = Modifier
    Decierdo_PE31Theme(
        dynamicColor = false
    ) {
        Scaffold(modifier.fillMaxSize()) {
                innerPadding -> Surface(
            modifier.padding(innerPadding),
            color = MaterialTheme.colorScheme.primary
        ) {
                MyApp()
            }
        }
    }
}