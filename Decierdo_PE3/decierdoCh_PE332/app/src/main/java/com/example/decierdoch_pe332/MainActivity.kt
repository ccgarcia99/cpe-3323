package com.example.decierdoch_pe332

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdoch_pe332.ui.theme.DecierdoCh_PE332Theme
import com.example.decierdoch_pe332.ui.theme.LocalSpacing
import com.example.decierdoch_pe332.ui.theme.LocalTextSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RootComposable()
        }
    }
}

@Composable
fun RootComposable(modifier: Modifier = Modifier) {
    val spacing = LocalSpacing.current
    val myTextSize = LocalTextSize.current
    DecierdoCh_PE332Theme(
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
                    val (labelRef, subHeadingRef, chipGroup) = createRefs()
                    TopLabel(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .constrainAs(labelRef) {
                                top.linkTo(parent.top, margin = spacing.marginRegularSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        customTextSize = myTextSize.extraLargeHeaderText

                    )
                    SubHeading(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .constrainAs(subHeadingRef) {
                                top.linkTo(labelRef.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        customTextSize = myTextSize.smallHeaderText
                    )
                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .constrainAs(chipGroup) {
                                top.linkTo(subHeadingRef.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        horizontalArrangement = Arrangement.Center
                    ) {
                        ChipOptions(
                            label = "Burger",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {}
                        ChipOptions(
                            label = "Pasta",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {}
                        ChipOptions(
                            label = "Orange Chicken",
                            modifier = Modifier.padding(horizontal = 8.dp)
                        ) {}
                    }
                }
            }
        }
    }
}
@Composable
fun CardListView() {
    LazyColumn {

    }
}

@Composable
fun SideDishCard(modifier: Modifier, imageId: Int) {
    Box(
        modifier = modifier
            .width(320.dp)
            .height(150.dp),
    ) {
        Surface(
            color = MaterialTheme.colorScheme.secondaryContainer,
            modifier = modifier.fillMaxSize(),
            shape = RoundedCornerShape(20.dp)
        ) {

        }
    }
}

@Composable
fun TopLabel(modifier: Modifier, customTextSize: TextUnit) {
    Text(
        text = "Little Sous",
        modifier = modifier,
        style = MaterialTheme.typography.titleLarge,
        color = MaterialTheme.colorScheme.primary,
        fontSize = customTextSize
    )
}

@Composable
fun SubHeading(modifier: Modifier, customTextSize: TextUnit) {
    Text(
        text = "your personal side-dish curator",
        textAlign = TextAlign.End,
        modifier = modifier,
        style = MaterialTheme.typography.titleSmall,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = customTextSize,
        fontStyle = FontStyle.Italic,
        letterSpacing = 0.5.sp
    )
}

@Composable
fun ChipOptions(modifier: Modifier = Modifier, label: String, onClick: () -> Unit) {
    var isSelected by remember { mutableStateOf(false) }
    FilterChip(
        selected = isSelected,
        onClick = { isSelected = !isSelected },
        label = { Text(text = label, maxLines = 1) },
        modifier = modifier,
        leadingIcon = {
            if (isSelected) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "some icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        }
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun AppPreview() {
    RootComposable()
}
