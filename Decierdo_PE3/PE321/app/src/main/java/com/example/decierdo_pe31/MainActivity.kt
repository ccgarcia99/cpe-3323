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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
    var billTotal by remember {
        mutableStateOf<Float?>(0f)
    }

    var tipPercentage by remember {
        mutableStateOf<Float?>(0f)
    }

    var testString by remember {
        mutableStateOf<String?>(null)
    }

    var calculatedTotal by remember {
        mutableStateOf<Float?>(null)
    }

    var roundUp by remember {
        mutableStateOf(false)
    }

    Column(
       modifier = modifier
           .padding(20.dp)
           .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Nondescript Bistro",
            fontFamily = FontFamily.Serif,
            fontSize = 36.sp
        )
        Text(
            text = "We are happy to serve you!",
            fontFamily = FontFamily.Serif,
            fontWeight = FontWeight.ExtraLight,
            fontSize = 16.sp,
            modifier = modifier
                .padding(
                    vertical = 10.dp
                )
        )
    }
    Column(
        modifier = modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        calculatedTotal?.let {
            BillTotal(
                totalBill = "%.2f".format(it)
            )
        }
        Spacer(modifier = modifier.height(20.dp))
        BillField(
            onValueChange = {
                newTotal -> billTotal = newTotal
            },
            onTestString = {
                newTotalString -> testString = newTotalString     // For debugging purposes
            }
        )
        Spacer(modifier = modifier.height(20.dp))
        RadioRowField(
            onPercentageSelected = {
                percentage -> tipPercentage = percentage
            }
        )
        RoundUp(
            onRoundUpChange = {
                roundUp = it
            }
        )
        CalculateTip(
            billInitial = billTotal,
            percentTip = tipPercentage,
            roundUp = roundUp,
            onCalculate = {
                total -> calculatedTotal = total
            }
        )
    }
}

@Composable
fun BillTotal(modifier: Modifier = Modifier, totalBill: String = "0.00") {
    Text(
        text = "PHP $totalBill",
        fontSize = 50.sp,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.ExtraLight,
        modifier = modifier
            .padding(20.dp)
    )
}

// function: Calculate Tip
// arguments: billInitial(float), percentTip(float), roundUp(Boolean)
// returns: billTotal
@Composable
fun CalculateTip(billInitial: Float?, percentTip: Float?, roundUp: Boolean, onCalculate: (Float) -> Unit) {
    OutlinedButton(
        onClick = {
            val billTotal = billInitial ?: 0f           // Null safety stuff
            val tipPercentage = percentTip ?: 0f
            var total = billTotal + (billTotal * tipPercentage)
            if (roundUp) {
                total = kotlin.math.ceil(total)
            }
            onCalculate(total)
        },
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Calculate Tip!",
            fontFamily = FontFamily.Serif,
            color = Color.Black
        )
    }
}



@Composable
fun RoundUp(
    modifier: Modifier = Modifier,
    onRoundUpChange: (Boolean) -> Unit
) {
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
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = Color.Black,
                    uncheckedThumbColor = Color.Black,
                    uncheckedTrackColor = Color.Gray
                ),
                onCheckedChange = {
                    toggled = it
                    onRoundUpChange(it)
                }
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
fun RadioRowField(
    modifier: Modifier = Modifier,
    onPercentageSelected: (Float) -> Unit
) {
    val percentages = arrayOf("10%", "15%", "20%")
    var selectedPercentage by remember { mutableStateOf("") }

    Column {
        Text(
            text = "Select tip",
            fontFamily = FontFamily.Serif,
            color = Color.Black // Ensure the text color is visible
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
                        onClick = {
                            selectedPercentage = item
                            val percentageValue = item.removeSuffix("%").toFloat() / 100
                            onPercentageSelected(percentageValue)
                        },
                        colors = RadioButtonDefaults.colors(
                            selectedColor = Color.Black,
                            unselectedColor = Color.Gray,
                            disabledSelectedColor = Color.LightGray
                        )
                    )
                    Text(
                        text = item,
                        fontSize = 20.sp,
                        fontFamily = FontFamily.Serif,
                        color = Color.Black // Ensure the text color is visible
                    )
                    Spacer(modifier = Modifier.width(8.dp)) // Add spacing between each radio button and text pair
                }
            }
        }
    }
}


@Composable
fun BillField(
    modifier: Modifier = Modifier,
    onValueChange: (Float?) -> Unit = {},   // Passing empty high-order functions
    onTestString: (String?) -> Unit = {}
) {
    var text by remember { mutableStateOf("") } // Variable to be used in the text field composable

    OutlinedTextField(
        value = text,
        onValueChange = {
            text = it
            val floatValue = it.toFloatOrNull()
            onValueChange(floatValue)
            onTestString(text)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
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