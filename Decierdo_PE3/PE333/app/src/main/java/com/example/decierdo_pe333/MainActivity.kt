package com.example.decierdo_pe333

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdo_pe333.ui.theme.Decierdo_PE333Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val modifier = Modifier

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootComposable(modifier = modifier)
        }
    }
}

@Composable
fun RootComposable(modifier: Modifier) {
    Decierdo_PE333Theme(
        dynamicColor = false,
        darkTheme = false
    ) {
        Scaffold {
            innerPadding -> Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface
            ) {
                ConstraintLayout {
                    val (
                        flvrTextRef,
                        imgBMIRef,
                        weightFld,
                        heightFld,
                        calcBtn
                    ) = createRefs()

                    FlavorText(
                        modifier = modifier
                            .constrainAs(flvrTextRef) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            }
                    )
                    // TODO: ADD Text output
                    BMIReference(
                        modifier = modifier
                            .constrainAs(imgBMIRef) {
                                top.linkTo(flvrTextRef.bottom, margin = 30.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            }
                            .aspectRatio(16.0f / 9.0f)
                    )
                    MyTextFields(
                        textLabel = "Enter Weight",
                        modifier = modifier
                            .constrainAs(weightFld) {
                                top.linkTo(imgBMIRef.bottom)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            }
                    )
                    MyTextFields(
                        textLabel = "Enter Height",
                        modifier = modifier
                            .constrainAs(heightFld) {
                                top.linkTo(weightFld.bottom, margin = (-20).dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                        }
                    )
                    CalculateBMI(
                        modifier = modifier
                            .constrainAs(calcBtn) {
                                bottom.linkTo(parent.bottom, margin = 50.dp)
                                start.linkTo(parent.start, margin = 60.dp)
                                end.linkTo(parent.end, margin = 60.dp)
                            }
                    )
                }
            }
        }
    }
}

@Composable
fun FlavorText(modifier: Modifier) {
    Text(
        text = "BMI Calculator",
        modifier = modifier,
        fontSize = 40.sp,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Monospace
    )
}

@Composable
fun BMIReference(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.ic_launcher_background),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun MyTextFields(
    textLabel: String,
    modifier: Modifier,
    onParamChange: (Float?) -> Unit = {}
) {
    var textInput by remember { mutableStateOf("")}
    OutlinedTextField(
        value = textInput,
        onValueChange = {
            textInput = it
            val floatValue = it.toFloatOrNull()
            onParamChange(floatValue)
        },
        label = { Text(text = textLabel)},
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
    )
}

@Composable
fun CalculateBMI(
    modifier: Modifier
) {
    ElevatedButton(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        onClick = { /*TODO*/ }
    ) {
        Text(text = "Calculate BMI")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun PreviewApp(modifier: Modifier = Modifier){
    RootComposable(modifier = modifier)
}