package com.example.decierdoch_pe331

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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.example.decierdoch_pe331.ui.theme.DecierdoCh_PE331Theme
import com.example.decierdoch_pe331.ui.theme.DeleteColorWay
import com.example.decierdoch_pe331.ui.theme.FinalColorWay
import com.example.decierdoch_pe331.ui.theme.NeutralColorWay

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
        darkTheme = false
    ) {
        var myTextInput by remember { mutableStateOf("") }
        val wordList = remember { mutableStateListOf<String>() }

        Scaffold { innerPadding ->
            Surface(
                modifier = modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                color = MaterialTheme.colorScheme.surface,
            ) {
                ConstraintLayout(
                    modifier = modifier.fillMaxSize()
                ) {
                    val (
                        txtRef, fieldRef, listRef, buttonsRowRef
                    ) = createRefs()

                    Text(
                        text = "!Wordy",
                        fontSize = 30.sp,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.secondary,
                        modifier = modifier
                            .constrainAs(txtRef) {
                                top.linkTo(parent.top, margin = 26.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    CustomOutlinedTextField(
                        value = myTextInput,
                        label = "Enter a word",
                        onValueChange = { input -> myTextInput = input },
                        modifier = modifier
                            .fillMaxWidth(0.9f)
                            .constrainAs(fieldRef) {
                                top.linkTo(txtRef.bottom, margin = 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            }
                    )

                    LazyColumn(
                        modifier = modifier
                            .fillMaxWidth()
                            .constrainAs(listRef) {
                                top.linkTo(fieldRef.bottom, margin = 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(buttonsRowRef.top, margin = 16.dp)
                                height = Dimension.fillToConstraints
                            }
                    ) {
                        items(wordList) { word ->
                            CustomWordCard(
                                textString = word,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp, horizontal = 16.dp)
                            )
                        }
                    }

                    Row(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp)
                            .constrainAs(buttonsRowRef) {
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                                bottom.linkTo(parent.bottom, margin = 16.dp)
                            },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        BespokeButton(
                            label = "Clear!!",
                            colorWays = DeleteColorWay,
                            onClick = { wordList.clear() },
                            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                        )

                        BespokeButton(
                            label = "Sort",
                            colorWays = NeutralColorWay,
                            onClick = { wordList.sort() },
                            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                        )

                        BespokeButton(
                            label = "Append!!",
                            colorWays = FinalColorWay,
                            onClick = {
                                if (myTextInput.isNotBlank()) {
                                    wordList.add(myTextInput)
                                    myTextInput = ""
                                }
                            },
                            modifier = Modifier.weight(1f).padding(horizontal = 4.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CustomWordCard(
    modifier: Modifier = Modifier,
    textString: String
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
        contentAlignment = Alignment.Center
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.secondary,
            shape = RoundedCornerShape(15.dp)
        ) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = textString,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 20.sp
                )
            }
        }
    }
}

@Composable
fun CustomOutlinedTextField(
    value: String,
    label: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(30.dp),
        modifier = modifier,
        label = { Text(label) },
        singleLine = true,
        textStyle = TextStyle(
            color = MaterialTheme.colorScheme.onSecondaryContainer,
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedLabelColor = MaterialTheme.colorScheme.onSecondaryContainer,
        )
    )
}

@Composable
fun BespokeButton(
    label: String,
    colorWays: ButtonColors,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedButton(
        onClick = onClick,
        colors = colorWays,
        modifier = modifier
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelLarge
        )
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
