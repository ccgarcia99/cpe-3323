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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.layoutId
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.colorpicker.ui.theme.ColorpickerTheme
import com.github.skydoves.colorpicker.compose.ColorEnvelope
import com.github.skydoves.colorpicker.compose.HsvColorPicker
import com.github.skydoves.colorpicker.compose.rememberColorPickerController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ColorpickerTheme{
                val colorViewModel: ColorViewModel = viewModel()
                MainScreen(colorViewModel = colorViewModel)
            }
        }
    }
}

// ViewModel to hold selected color
class ColorViewModel : ViewModel() {
    private val _selectedColor = MutableStateFlow(Color.Red)
    val selectedColor: StateFlow<Color> = _selectedColor

    fun updateColor(color: Color) {
        _selectedColor.value = color
    }
}

@Composable
fun MainScreen(
    colorViewModel: ColorViewModel,
    modifier: Modifier = Modifier
) {
    val showDialog = remember { mutableStateOf(false) }
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
            showDialog = showDialog,
            modifier = Modifier.layoutId("button")
        )
        ColorBox(
            color = selectedColor,
            modifier = Modifier.layoutId("colorBox")
        )

        if (showDialog.value) {
            ColorPickerDialog(
                showDialog = showDialog,
                colorViewModel = colorViewModel
            )
        }
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
fun ColorPickerPrompt(showDialog: MutableState<Boolean>, modifier: Modifier = Modifier) {
    ElevatedButton(
        modifier = modifier,
        onClick = {
            showDialog.value = true
        }
    ) {
        Text(text = "Pick a color!")
    }
}

@Composable
fun ColorPickerDialog(
    showDialog: MutableState<Boolean>,
    colorViewModel: ColorViewModel,
) {
    val colorController = rememberColorPickerController()
    AlertDialog(
        onDismissRequest = { showDialog.value = false },
        confirmButton = {
            TextButton(onClick = { showDialog.value = false }) {
                Text("Done")
            }
        },
        title = {
            Text(text = "Pick a Color")
        },
        text = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HsvColorPicker(
                    modifier = Modifier
                        .fillMaxWidth(0.8f)
                        .height(450.dp),
                    controller = colorController,
                    onColorChanged = { colorEnvelope: ColorEnvelope ->
                        colorViewModel.updateColor(colorEnvelope.color)
                    }
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { showDialog.value = false }) {
                Text("Cancel")
            }
        },
        modifier = Modifier.padding(16.dp) // Ensure proper padding around the dialog
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun ScreenPreview(modifier: Modifier = Modifier) {
    val colorViewModel: ColorViewModel = viewModel()
    MainScreen(
        colorViewModel = colorViewModel
    )
}
