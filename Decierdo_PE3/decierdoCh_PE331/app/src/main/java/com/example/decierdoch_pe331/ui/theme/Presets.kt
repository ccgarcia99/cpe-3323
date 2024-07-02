package com.example.decierdoch_pe331.ui.theme

import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

// Delete button colorways
val DeleteColorWay: ButtonColors @Composable get() = ButtonDefaults.elevatedButtonColors(
    containerColor = MaterialTheme.colorScheme.errorContainer,
    contentColor = MaterialTheme.colorScheme.onErrorContainer,
)

val NeutralColorWay: ButtonColors @Composable get() = ButtonDefaults.elevatedButtonColors(
    containerColor = MaterialTheme.colorScheme.inverseSurface,
    contentColor = MaterialTheme.colorScheme.inverseOnSurface
)

val FinalColorWay: ButtonColors @Composable get() = ButtonDefaults.elevatedButtonColors(
    contentColor = MaterialTheme.colorScheme.tertiaryContainer,
    containerColor = MaterialTheme.colorScheme.onTertiaryContainer
)

@Composable
fun TestFun(
    colorways: ButtonColors = FinalColorWay // test
){
    ElevatedButton(
        onClick = { /*TODO*/ },
        colors = colorways
    ) {
        Text(text = "Sample T3xT!")
    }
}

// Test colorways of buttons here
@Preview(
    showBackground = true,
)
@Composable
fun PreviewComponent(
){
    DecierdoCh_PE331Theme(
        dynamicColor = false,
        darkTheme = true
    ) {
        TestFun()
    }
}