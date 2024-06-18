package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp.ui.theme.TestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestAppTheme {
                MyApp(modifier = Modifier.fillMaxSize())
            }
        }
    }
}

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = listOf("Test", "List", "Android")
) {
    var shouldShowOnboarding by remember { mutableStateOf(true) }
    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        MyListView(modifier, names)
    }
}

@Composable
fun MyListView(
    modifier: Modifier,
    names: List<String>
) {
    Column(modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            MyListBox(name = name)
        }
    }
}


// A UI element that will contain an element of a list. Iterated n number of times
// for each element in the list.
//@SuppressLint("RememberReturnType")
@Composable
fun MyListBox(name: String, modifier: Modifier = Modifier) {
    val expanded = remember { mutableStateOf(false) }
    val expandPadding = if(expanded.value) 48.dp else 0.dp
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = expandPadding)) {
                Text(text = "Hello ")
                Text(text = name)
            }
            ElevatedButton(
                onClick = { expanded.value = !expanded.value }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }
    }
}

@Composable
fun OnboardingScreen(modifier: Modifier = Modifier, onContinueClicked: () -> Unit) {

    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Welcome to Codelab Basics!")
        Button(
            modifier = Modifier.padding(vertical = 24.dp),
            onClick = onContinueClicked
        ) {
            Text(text = "Continue")    
        }
    }
}

// Preview toolkit.
@Preview(showBackground = true, widthDp = 320)
@Composable
fun GreetingPreview() {
    TestAppTheme {
        MyApp()
    }
}
