package com.example.decierdoch_pe332

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    // Access local pre-defined spacings and text sizes
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
                    val (labelRef, subHeadingRef) = createRefs()
                    TopLabel(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .constrainAs(labelRef){
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
                            .constrainAs(subHeadingRef){
                                top.linkTo(labelRef.bottom, margin = spacing.smallSpacing)
                                start.linkTo(parent.start, margin = spacing.marginRegularSpacing)
                                end.linkTo(parent.end, margin = spacing.marginRegularSpacing)
                            },
                        customTextSize = myTextSize.smallHeaderText)
                }
            }
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

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    RootComposable()
}
