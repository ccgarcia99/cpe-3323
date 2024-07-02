package com.example.decierdoch_pe331

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.decierdoch_pe331.ui.theme.DecierdoCh_PE331Theme

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
    DecierdoCh_PE331Theme(
        dynamicColor = false,
        darkTheme = true
    ) {
        Scaffold {
            innerPadding -> Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface,
            ) {
                ConstraintLayout{
                   val (txtRef, btnRef) = createRefs()
                    Text(
                        text = "Mr. Robot",
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onSurface,
                        modifier = modifier
                            .constrainAs(txtRef) {
                                top.linkTo(parent.top, margin = 26.dp)
                                start.linkTo(parent.start, margin = 26.dp)
                                end.linkTo(parent.end, margin = 26.dp)
                            }
                    )
                    ElevatedButton(
                        colors = ButtonColors(
                            contentColor = MaterialTheme.colorScheme.onTertiary,
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            disabledContentColor = MaterialTheme.colorScheme.primary,
                            disabledContainerColor = MaterialTheme.colorScheme.primaryContainer
                        ),
                        modifier = modifier
                            .constrainAs(btnRef) {
                                top.linkTo(txtRef.bottom, margin = 26.dp)
                                start.linkTo(parent.start, margin = 26.dp)
                                end.linkTo(parent.end, margin = 26.dp)
                                bottom.linkTo(parent.bottom, margin = 26.dp)
                            },
                        onClick = { /*TODO*/ }) {
                        Text(text = "You are my Medicine")
                    }
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
fun AppPreview() {
    RootComposable()
}