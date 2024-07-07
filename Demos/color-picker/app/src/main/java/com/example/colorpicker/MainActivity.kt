package com.example.colorpicker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.ColorPickerController
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

// ViewModel to hold selected color
class ColorViewModel : ViewModel() {
    private val _selectedColor = MutableStateFlow(Color.Red)
    val selectedColor: StateFlow<Color> = _selectedColor

    fun updateColor(color: Color) {
        _selectedColor.value = color
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            val colorController = rememberColorPickerController()
            val colorViewModel: ColorViewModel = viewModel()

            NavHost(navController = navController, startDestination = "MainScreen") {
                composable(route = "MainScreen") {
                    MainScreen(navController = navController, colorViewModel = colorViewModel)
                }
                composable(route = "ColorPickerScreen") {
                    ColorPickerScreen(
                        colorPicker = colorController,
                        colorViewModel = colorViewModel
                    )
                }
            }
        }
    }
}

// Color picker screen
@Composable
fun ColorPickerScreen(
    colorPicker: ColorPickerController,
    colorViewModel: ColorViewModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HsvColorPicker(
            modifier = modifier
                .fillMaxWidth(0.8f)
                .height(450.dp)
                .width(10.dp),
            controller = colorPicker,
            onColorChanged = { colorEnvelope: ColorEnvelope ->
                colorViewModel.updateColor(colorEnvelope.color)
            }
        )
    }
}

// Main screen
@Composable
fun MainScreen(
    navController: NavController,
    colorViewModel: ColorViewModel,
    modifier: Modifier = Modifier
) {
    val selectedColor by colorViewModel.selectedColor.collectAsState()

    val constraints = ConstraintSet {
        val button = createRefFor("button")
        val colorBox = createRefFor("colorBox")

        createVerticalChain(
            button.withChainParams(bottomMargin = 18.dp),
            colorBox,
            chainStyle = ChainStyle.Packed
        )

        constrain(button) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }

        constrain(colorBox) {
            top.linkTo(button.bottom)
            bottom.linkTo(parent.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }
    ConstraintLayout(
        constraintSet = constraints,
        modifier = modifier.fillMaxSize()
    ) {
        ColorPickerPrompt(
            navController = navController,
            modifier = Modifier.layoutId("button")
        )
        ColorBox(
            color = selectedColor,
            modifier = Modifier.layoutId("colorBox")
        )
    }
}

@Composable
fun ColorBox(color: Color, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .width(90.dp)
            .height(90.dp)
    ) {
        Surface(
            color = color,
            modifier = modifier
                .fillMaxSize()
                .clip(CircleShape)
        ) {
            // NOP
        }
    }
}

@Composable
fun ColorPickerPrompt(navController: NavController, modifier: Modifier = Modifier) {
    ElevatedButton(
        modifier = modifier,
        onClick = {
            navController.navigate("ColorPickerScreen")
        }
    ) {
        Text(text = "Pick a color!")
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScreenPreview(modifier: Modifier = Modifier) {
    val mockNavController = rememberNavController()
    val colorViewModel: ColorViewModel = viewModel()
    MainScreen(
        navController = mockNavController,
        colorViewModel = colorViewModel
    )
}
