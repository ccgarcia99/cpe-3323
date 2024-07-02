package com.example.decierdo_pe323

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdo_pe333.R
import com.example.decierdo_pe333.ui.theme.Decierdo_PE333Theme
import kotlin.math.pow

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
fun RootComposable(modifier: Modifier = Modifier) {

    var personWeight by remember {
        mutableStateOf<Float?>(0f)
    }

    var personHeight by remember {
        mutableStateOf<Float?>(0f)
    }
    var bMI by remember {
        mutableStateOf<Float?>(0f)
    }

    Decierdo_PE333Theme(
        dynamicColor = false,
        darkTheme = false
    ) {
        Scaffold { innerPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface
            ) {
                ConstraintLayout {
                    val (
                        flvrTextRef,
                        imgBMIRef,
                        txtRef,
                        weightFld,
                        heightFld,
                        calcBtn
                    ) = createRefs()

                    FlavorText(
                        modifier = Modifier
                            .constrainAs(flvrTextRef) {
                                top.linkTo(parent.top, margin = 20.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            }
                    )
                    bMI?.let {
                        BMIReference(
                            bmiIn = it,
                            modifier = Modifier
                                .aspectRatio(16 / 9f)
                                .constrainAs(imgBMIRef) {
                                    top.linkTo(flvrTextRef.bottom, margin = 20.dp)
                                    start.linkTo(parent.start, margin = 20.dp)
                                    end.linkTo(parent.end, margin = 20.dp)
                                }
                        )
                    }
                    // TODO: ADD Text output
                    bMI?.let {
                        OutputText(
                            bmiOut = "%.2f".format(it),
                            modifier = Modifier
                                .constrainAs(txtRef) {
                                    top.linkTo(imgBMIRef.bottom, margin = 20.dp)
                                    start.linkTo(parent.start, margin = 20.dp)
                                    end.linkTo(parent.end, margin = 20.dp)
                                }
                        )
                    }
                    MyTextFields(
                        textLabel = "Enter height(in cm)",
                        modifier = modifier
                            .constrainAs(heightFld) {
                                top.linkTo(txtRef.bottom, margin = (-15).dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            },
                        onParamChange = {
                            newHeight -> personHeight = newHeight
                        }
                    )
                    MyTextFields(
                        textLabel ="Enter weight(in kg)",
                        modifier = modifier
                            .constrainAs(weightFld) {
                                top.linkTo(heightFld.bottom, margin = (-35).dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            },
                        onParamChange = {
                            newWeight -> personWeight = newWeight
                        }
                    )
                    CalculateBMI(
                        personWeight = personWeight,
                        personHeight = personHeight,
                        modifier = modifier
                            .constrainAs(calcBtn) {
                                bottom.linkTo(parent.bottom, margin = 50.dp)
                                start.linkTo(parent.start, margin = 20.dp)
                                end.linkTo(parent.end, margin = 20.dp)
                            },
                        returnBMI = {
                            newBMI -> bMI = newBMI
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
        fontFamily = FontFamily.Default,
        letterSpacing = 2.sp
    )
}

@Composable
fun BMIReference(
    bmiIn: Float,
    modifier: Modifier) {

    val bmiCategory = when {
        bmiIn < 10.0 -> R.drawable.ic_launcher_background
        bmiIn < 18.5 -> R.drawable.underweight
        bmiIn < 24.9 -> R.drawable.normal
        bmiIn < 29.9 -> R.drawable.overweight
        else -> R.drawable.obese
    }

    Image(
        painter = painterResource(id = bmiCategory),
        contentDescription = null,
        modifier = modifier
    )
}

@Composable
fun OutputText(
    modifier: Modifier,
    bmiOut: String
) {
    val bmiValue = bmiOut.toFloatOrNull()
    val bmiRange = mapOf(
        1 to "Underweight",
        2 to "Normal",
        3 to "Overweight",
        4 to "Obese"
    )
    if (bmiValue == 0f) {
        Text(
            text = """
            Enter your height and
            weight below!
            """.trimIndent(),
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            lineHeight = 35.sp
        )
    }else {
        val bmiCategory = when {
            bmiValue == null -> "Invalid BMI"
            bmiValue < 10.0 -> ""
            bmiValue < 18.5 -> bmiRange[1] ?: "Underweight"
            bmiValue < 24.9 -> bmiRange[2] ?: "Normal"
            bmiValue < 29.9 -> bmiRange[3] ?: "Overweight"
            else -> bmiRange[4] ?: "Obese"
        }

        Text(
            text = """
            Your BMI: $bmiOut
            $bmiCategory
        """.trimIndent(),
            modifier = modifier,
            textAlign = TextAlign.Center,
            fontSize = 28.sp,
            lineHeight = 35.sp
        )
    }
}

@Composable
fun MyTextFields(
    textLabel: String,
    modifier: Modifier = Modifier,
    onParamChange: (Float?) -> Unit = {}
) {
    var textInput by remember { mutableStateOf("") }
    OutlinedTextField(
        value = textInput,
        onValueChange = {
            textInput = it
            val floatValue = it.toFloatOrNull()
            onParamChange(floatValue)
        },
        label = { Text(text = textLabel) },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal
        ),
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(50.dp),
        textStyle = TextStyle(
            color = Color.Black,
            fontSize = 18.sp
        )
    )
}


@Composable
fun CalculateBMI(
    modifier: Modifier,
    personWeight: Float?,
    personHeight: Float?,
    returnBMI: (Float) -> Unit
) {
    ElevatedButton(
        modifier = modifier
            .padding(20.dp)
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black,
            disabledContentColor = Color.Gray,
            disabledContainerColor = Color.LightGray
        ),
        border = BorderStroke(
            width = 1.dp,
            brush = SolidColor(Color.Black)
        ),
        onClick = {
            // Ensure height and weight are not null
            val heightMeters = personHeight?.times(0.01f)
            if (heightMeters != null && personWeight != null) {
                val bmi = personWeight / heightMeters.pow(2)
                returnBMI(bmi)
            }
        }
    ) {
        Text(
            text = "Calculate BMI",
            fontSize = 18.sp
        )
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