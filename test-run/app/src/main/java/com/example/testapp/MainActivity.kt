package com.example.testapp

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
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

// function: MyApp
// desc: Common ancestor of all composable functions. Important check flags like
//       shouldShowOnboarding variable reside here. The `Modifier` variable is first
//       passed through here from the main class.
// arguments: modifier - Modifier, names - List<String> w/ List of 1..10 as default
@Composable
fun MyApp(
    modifier: Modifier = Modifier,
    names: List<String> = List(1000){"$it"}
) {
    // Check flag if button on OnboardingScreen function is pressed or not.
    // shouldShowOnboardings value is provided by the lambda function `remember`
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    // Check condition
    if(shouldShowOnboarding) {
        OnboardingScreen(onContinueClicked = {shouldShowOnboarding = false})
    } else {
        MyListView(modifier, names)
    }
}

// function: MyListView
// desc: This function builds a scrollable list on the UI with `LazyColumn`.
// arguments: names - List<String> w/ List of 1..1000 as default, modifier: Modifier
@Composable
fun MyListView(
    modifier: Modifier,
    names: List<String> = List(1000){"$it"}
) {
    LazyColumn(modifier = modifier.padding(vertical = 4.dp)) {
        items(items = names) { name -> MyListBox(name = name)}
    }
}

// function: MyListBox
// desc: Renders a box container with a text element and an elevated button inside,
//       is iteratively called & rendered through the `MyListView` function
// arguments: name - String, modifier - Modifier w/ Modifier as default parameter
@Composable
fun MyListBox(name: String, modifier: Modifier = Modifier) {
    // Check flag if button is pressed or not
    var expanded by rememberSaveable { mutableStateOf(false) }
    val expandPadding by animateDpAsState(
        targetValue = if(expanded) 48.dp else 0.dp,
        label = "" // If we add animations when expanding, the app crashes
    )
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = expandPadding)) {
                Text(text = "Hello ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.ExtraBold,
                    fontFamily = FontFamily.Monospace
                )
            }
            ElevatedButton(
                onClick = { expanded = !expanded }
            ) {
                Text(if (expanded) "Show less" else "Show more")
            }
        }
    }
}

// function:    OnboardingScreen
// desc:        A composable function that renders an onboarding screen when opening the app
// arguments:   modifier - Modifier, onContinueClicked - void lambda function
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
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "GreetingPreviewDark"
)
@Preview(showBackground = true, widthDp = 320, name = "GreetingPreviewNormal")
@Composable
fun GreetingPreview() {
    TestAppTheme {
        MyApp()
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "ListPreviewNight"
)
@Preview(showBackground = true, widthDp = 320, name = "ListPreviewNormal")
@Composable
fun ListPreview() {
    val modifier : Modifier = Modifier
    TestAppTheme {
        MyListView(modifier = modifier)
        // Passing a local modifier so that the
        // compiler won't complain(plus it's a required parameter)
    }
}

