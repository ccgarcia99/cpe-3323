package com.example.decierdoch_le342

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.decierdoch_le342.ui.theme.DecierdoCh_LE342Theme

val colorList: List<Long> = listOf(
    0xFFFF0000, // Red
    0xFF00FF00, // Green
    0xFF0000FF, // Blue
)


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MainScreen()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    var selectedColors by remember { mutableStateOf(setOf<Long>()) }
    val blendedColor = remember(selectedColors) {
        if (selectedColors.isEmpty()) Color.Black else Color(blendColors(selectedColors))
    }

    DecierdoCh_LE342Theme {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = "Picasso",
                        fontWeight = FontWeight.Medium
                    )
                })
            }
        ) { innerPadding ->
            Surface(
                color = MaterialTheme.colorScheme.surface,
                modifier = modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                val constraints = ConstraintSet {
                    val blendColorBox = createRefFor("blendColorBox")
                    val textBox = createRefFor("textBox")
                    val horzBar = createRefFor("horzBar")
                    val rowColorSwitch = createRefFor("rowColorSwitch")

                    constrain(blendColorBox) {
                        top.linkTo(parent.top, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    constrain(textBox) {
                        top.linkTo(blendColorBox.bottom, margin = 16.dp)
                        bottom.linkTo(horzBar.top, margin = 24.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    constrain(horzBar) {
                        top.linkTo(textBox.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    constrain(rowColorSwitch) {
                        top.linkTo(horzBar.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                }
                ConstraintLayout(
                    constraintSet = constraints,
                    modifier = modifier.fillMaxSize()
                ) {
                    BlendColorBox(
                        modifier = Modifier.layoutId("blendColorBox"),
                        blendColor = blendedColor
                    )
                    TextBox(modifier = Modifier.layoutId("textBox"))
                    HorizontalBorder(modifier = modifier.layoutId("horzBar"))
                    SwitchRow(
                        modifier = Modifier.layoutId("rowColorSwitch"),
                        onColorSelected = { color, isSelected ->
                            selectedColors = if (isSelected) {
                                selectedColors + color
                            } else {
                                selectedColors - color
                            }
                        }
                    )
                }
            }
        }
    }
}

fun blendColors(colors: Set<Long>): Long {
    if (colors.isEmpty()) return 0xFF000000 // Black if no colors selected
    var r = 0
    var g = 0
    var b = 0
    colors.forEach { color ->
        r = r or (color shr 16 and 0xFF).toInt()
        g = g or (color shr 8 and 0xFF).toInt()
        b = b or (color and 0xFF).toInt()
    }
    return (0xFF000000 or (r shl 16).toLong() or (g shl 8).toLong() or b.toLong())
}

@Composable
fun HorizontalBorder(modifier: Modifier) {
    Box(
        modifier = modifier
            .fillMaxWidth(0.9f)
            .height(1.dp)
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(2.dp)),
            color = MaterialTheme.colorScheme.outline
        ) {
        }
    }
}

@Composable
fun TextBox(modifier: Modifier) {
    Text(
        text = "Turn any switch ON to create color combinations",
        modifier = modifier.padding(8.dp),
        textAlign = TextAlign.Center,
        fontSize = 24.sp,
        fontWeight = FontWeight.Light
    )
}

@Composable
fun BlendColorBox(modifier: Modifier = Modifier, blendColor: Color) {
    Box(
        modifier = modifier
            .width(150.dp)
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier
                .clip(CircleShape)
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.outline
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = modifier
                        .clip(CircleShape)
                        .fillMaxSize(0.98f),
                    color = blendColor
                ) {
                }
            }
        }
    }
}

@Composable
fun SwitchRow(
    modifier: Modifier,
    onColorSelected: (Long, Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        colorList.forEach { color ->
            SelectionColumn(color = color, onColorSelected = onColorSelected)
        }
    }
}

@Composable
fun SelectionColumn(
    modifier: Modifier = Modifier,
    color: Long,
    onColorSelected: (Long, Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(false) }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ColorBox(modifier, color)
        Spacer(modifier = modifier.padding(4.dp))
        Switch(
            checked = checked,
            onCheckedChange = {
                checked = it
                onColorSelected(color, it)
            }
        )
    }
}

@Composable
private fun ColorBox(modifier: Modifier, color: Long) {
    Box(
        modifier = modifier
            .width(45.dp)
            .height(45.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.outline
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.Center
            ) {
                Surface(
                    modifier = modifier
                        .fillMaxSize(0.95f),
                    color = Color(color)
                ) {
                }
            }
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview(modifier: Modifier = Modifier) {
    MainScreen(
        modifier = modifier
    )
}
